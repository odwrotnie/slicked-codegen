package test

import model.{HasCompanyHelper, HasEventHelper, Tables}
import org.scalatest.FlatSpec
import slicked.SlickedDatabaseConfig

class HasEntityHelperTest
  extends FlatSpec
    with SlickedDatabaseConfig
    with HasEventHelper
    with HasCompanyHelper
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
