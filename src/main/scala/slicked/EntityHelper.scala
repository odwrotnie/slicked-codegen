package slicked

import scala.concurrent.Future

trait HasEntityWithIdHelper
  extends WithEntityHelper {

  self: DatabaseProfile =>
  import profile.api._

  abstract class EntityWithIdHelper[E <: {def id: Long}, T <: Table[E] {def id: Rep[Long]}]
    extends EntityHelper[E, T] {

    def table: TableQuery[T]

    // TODO - http://stackoverflow.com/questions/41527702/scala-monocle-cannot-find-method-id-in-e
    // import monocle.Lens
    // import monocle.macros.GenLens
    // def idLens: Lens[E, Long] = GenLens[E](_.id)
    // def withId(e: E)(id: Long) = idLens.set(id)(e)

    def withId(e: E)(id: Long): E

    override def insert(e: E): DBIO[E] = {
      require(e.id < 0, s"Inserting entity $e with defined id: ${e.id}")
      val newE = beforeInsert(e)
      val idAction = (table returning table.map(_.id)) += e
      idAction.map { id: Long =>
        val newEWithId = withId(e)(id)
        afterInsert(newEWithId)
        newEWithId
      }
    }
  }
}

trait WithEntityHelper
  extends SlickSupport {

  self: DatabaseProfile =>
  import profile.api._

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
