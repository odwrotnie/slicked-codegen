package test

import model.{CompanyHelper, EventHelper, Tables}
import org.scalatest.FlatSpec
import slicked.{SlickSupport, SlickedDatabaseConfig}

class EntityHelperTest
  extends FlatSpec
    with SlickedDatabaseConfig
    with EventHelper
    with CompanyHelper
    with Tables {

  "Helper" should "help" in {
    val e = Event(None, Some("INSERT"), None)
    e.insert
    e.onlyEventMethod
    val c = Company(-1, "Subeli", None)
    c.insert
    c.onlyCompanyMethod
    assert(eventHelper.stream.toList.size == 3)
  }
}
