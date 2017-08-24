package slicked.helpers

import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.meta.MTable
import slicked._

import scala.concurrent.Future

trait EntityHelper extends SlickSupport with LazyLogging {

  self: HasDatabaseProfile with HasDatabaseInstance =>

  import profile.api._

  type ENT <: Any
  type TBL <: Table[ENT]

  def table: TableQuery[TBL]
  def createSchema(): Unit =
    MTable.getTables(tableName).await.headOption match {
      case Some(mt) =>
        val columns = mt.getColumns.await.map(c => s"${c.name}:${c.typeName}")
        logger.debug(
          s"Table $tableName already exists - ${columns.mkString(", ")}")
      case None =>
        logger.info(s"Creating table $tableName")
        table.schema.create.await
    }
  final lazy val tableName: String = table.baseTableRow.tableName

  def delete(query: Query[TBL, ENT, Seq]): DBIO[Int] = query.delete
  def deleteAll(): DBIO[Int] = delete(allQuery)

  //def insert(e: Iterable[ENT]): DBIO[Iterable[ENT]] = DBIO.sequence(e.map(insert))
  def insert(e: ENT): DBIO[ENT] = {
    val newE = beforeInsert(e)
    (table += newE).named(s"Insert $e").map(i => newE)
  } map { e =>
    afterInsert(e)
    e
  }

  def getOrInsert(query: Query[_, ENT, Seq], e: ENT): DBIO[ENT] =
    query.length.result.flatMap {
      case i if i == 0 => insert(e)
      case i if i == 1 => query.result.head
      case i =>
        val results: Seq[ENT] = query.result.await
        DBIO.failed(new Exception(
          s"Get ${getClass.getSimpleName} or insert $e query returned more than 1 ($i) row: ${results
            .mkString(", ")}"))
    }

  def updateByQuery(query: Query[_, ENT, Seq], e: ENT): DBIO[Int] =
    query.update(e)
  def updateOrInsert(query: Query[_, ENT, Seq], e: ENT): DBIO[ENT] =
    query.length.result.flatMap {
      case i if i == 0 => insert(e)
      case i if i == 1 => updateByQuery(query, e).map(_ => e)
      case i =>
        val results: Seq[ENT] = query.result.await
        DBIO.failed(new Exception(
          s"Update ${getClass.getSimpleName} or insert $e query returned more than 1 ($i) row: ${results
            .mkString(", ")}"))
    }

  def allQuery: Query[TBL, ENT, Seq] = table
  def stream(query: Query[TBL, ENT, Seq]): Stream[ENT] = streamify(query)
  def stream: Stream[ENT] = stream(allQuery)

  def page(pageNum: Long, pageSize: Long): Future[Seq[ENT]] =
    page(allQuery, pageNum, pageSize)
  def pages(pageSize: Long): Future[Long] = pages(allQuery, pageSize)

  def page(query: Query[TBL, ENT, Seq],
           pageNum: Long,
           pageSize: Long): Future[Seq[ENT]] = page(query, pageNum, pageSize)
  def pages(query: Query[TBL, ENT, Seq], pageSize: Long): Future[Long] =
    pages(query, pageSize)

  // BEFORE
  def beforeInsert(e: ENT): ENT = e

  // AFTER
  def afterInsert(e: ENT): Unit = {
    logger.debug(s"Inserted (${getClass.getSimpleName.replace("$", "")}): $e")
  }
}
