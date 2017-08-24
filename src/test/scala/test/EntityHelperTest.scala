package test

import model.{EventHelper, Tables}
import org.joda.time.DateTime
import org.scalatest.FlatSpec

class EntityHelperTest
  extends FlatSpec {

  "Helper" should "help" in {
    EventHelper.createSchema
    val e = EventHelper.Event(None, Some("INSERT " + DateTime.now), None)
    EventHelper.insert(e).await
    val events = EventHelper.stream.take(1000)
    events foreach { e =>
      println(s"E: $e")
    }
  }
}
