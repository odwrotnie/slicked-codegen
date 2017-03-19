package slicked.helpers

import slicked._

import scala.concurrent.Future

trait HasEntityWithIdHelper
  extends HasEntityHelper {

  self: DatabaseProfile =>
  import profile.api._

  type E <: {def id: Long}
  type T <: Table[E] {def id: Rep[Long]}

  abstract class EntityWithIdHelper
    extends EntityHelper {

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
