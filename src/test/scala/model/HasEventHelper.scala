//package model
//
//import slicked.DatabaseProfile
//import slicked.helpers.HasEntityHelper
//
//trait HasEventHelper
//  extends HasEntityHelper {
//
//  self: DatabaseProfile with Tables =>
//
//  val eventHelper = new EntityHelper[Event, EventTable] {
//    override def table = EventTable
//  }
//
//  implicit class RichEvent(e: Event)
//    extends RichEntity[Event, EventTable](e) {
//    override def helper = eventHelper
//    def onlyEventMethod: String = e.name.getOrElse("") + e.desc.getOrElse("")
//  }
//}
