package model

import slicked.SlickedDatabaseConfig
import slicked.helpers.EntityHelper

object EventHelper extends EntityHelper with SlickedDatabaseConfig with Tables {

  override type ENT = Event
  override type TBL = EventTable
  override val table = EventTable
}
