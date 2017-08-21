package test

import model.{EventHelper, Tables}
import org.scalatest.FlatSpec

class EntityHelperTest
  extends FlatSpec {

  "Helper" should "help" in {
    val e = EventHelper.Event(None, Some("INSERT"), None)
    EventHelper.insert(e)
  }
}
