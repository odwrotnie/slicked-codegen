package model

import slicked.SlickedDatabaseConfig
import slicked.helpers.EntityHelper

class EventHelper
  extends EntityHelper
    with Tables
    with SlickedDatabaseConfig {

  override type ENT = Event
  override type TBL = EventTable

  override def table = EventTable
}
