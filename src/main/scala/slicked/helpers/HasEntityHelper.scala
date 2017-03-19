package slicked.helpers

import slicked._

import scala.concurrent.Future

trait HasEntityHelper
  extends SlickSupport {

  self: DatabaseProfile =>
  import profile.api._

  abstract class RichEntity[E, T <: Table[E]](e: E) {
    def helper: EntityHelper[E, T]
    def insert: DBIO[E] = helper.insert(e)
    def getOrInsert(query: Query[_, E, Seq]): DBIO[E] = helper.getOrInsert(query, e)
    def updateByQuery(query: Query[_, E, Seq]): DBIO[Int] = helper.updateByQuery(query, e)
    def updateOrInsert(query: Query[_, E, Seq], e: E): DBIO[E] = helper.updateOrInsert(query, e)
  }

  abstract class EntityHelper[E, T <: Table[E]] {

    def table: TableQuery[T]

    def delete(query: Query[T, E, Seq]): DBIO[Int] = query.delete
    def deleteAll(): DBIO[Int] = delete(allQuery)

    def insert(e: Iterable[E]): DBIO[Iterable[E]] = DBIO.sequence(e.map(insert))
    def insert(e: E): DBIO[E] = {
      val newE = beforeInsert(e)
      (table += newE).named(s"Insert $e").map(i => newE)
    } map { e =>
      afterInsert(e)
      e
    }

    def getOrInsert(query: Query[_, E, Seq], e: E): DBIO[E] = query.length.result.flatMap {
      case i if i == 0 => insert(e)
      case i if i == 1 => query.result.head
      case i =>
        val results: Seq[E] = query.result.await
        DBIO.failed(new Exception(s"Get ${ getClass.getSimpleName } or insert $e query returned more than 1 ($i) row: ${ results.mkString(", ") }"))
    }

    def updateByQuery(query: Query[_, E, Seq], e: E): DBIO[Int] = query.update(e)
    def updateOrInsert(query: Query[_, E, Seq], e: E): DBIO[E] = query.length.result.flatMap {
      case i if i == 0 => insert(e)
      case i if i == 1 => updateByQuery(query, e).map(_ => e)
      case i =>
        val results: Seq[E] = query.result.await
        DBIO.failed(new Exception(s"Update ${ getClass.getSimpleName } or insert $e query returned more than 1 ($i) row: ${ results.mkString(", ") }"))
    }

    def allQuery: Query[T, E, Seq] = table
    def stream(query: Query[T, E, Seq]): Stream[E] = streamify(query)
    def stream: Stream[E] = stream(allQuery)

    def page(pageNum: Long, pageSize: Long): Future[Seq[E]] = page(allQuery, pageNum, pageSize)
    def pages(pageSize: Long): Future[Long] = pages(allQuery, pageSize)

    def page(query: Query[T, E, Seq], pageNum: Long, pageSize: Long): Future[Seq[E]] = page(query, pageNum, pageSize)
    def pages(query: Query[T, E, Seq], pageSize: Long): Future[Long] = pages(query, pageSize)

    // BEFORE
    def beforeInsert(e: E): E = e

    // AFTER
    def afterInsert(e: E): Unit = {
      logger.debug(s"Inserted (${ getClass.getSimpleName.replace("$", "") }): $e")
    }
  }
}
