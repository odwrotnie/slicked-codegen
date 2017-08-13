package test

import model.{EventHelper, Tables}
import org.scalatest.FlatSpec

class EntityHelperTest
  extends FlatSpec
    with Tables {

  "Helper" should "help" in {
    val e = Event(None, Some("INSERT"), None)
    EventHelper.insert(e)
  }
}
