package slicked.helpers

import slicked._

trait EntityWithIdHelper[API]
  extends EntityHelper[API] {

  self: DatabaseProfile =>
  import profile.api._

  type ENT <: { def id: Long }
  type TBL <: Table[ENT] { def id: Rep[Long] }

  def table: TableQuery[TBL]

  def withId(e: ENT)(id: Long): ENT

  override def insert(e: ENT): DBIO[ENT] = {
    require(e.id < 0, s"Inserting entity $e with defined id: ${e.id}")
    val newE = beforeInsert(e)
    val idAction = (table returning table.map(_.id)) += e
    idAction.map { id: Long =>
      val newEWithId = withId(e)(id)
      afterInsert(newEWithId)
      newEWithId
    }
  }

  def update(e: ENT): DBIO[Int] = {
    val q = table.filter(_.id === e.id)
    q.update(e)
  }

  def byId(id: Long): DBIO[Option[ENT]] = {
    table.filter(_.id === id).result.headOption
  }

  def byIdGet(id: Long): DBIO[ENT] = {
    table.filter(_.id === id).result.head
  }
}
