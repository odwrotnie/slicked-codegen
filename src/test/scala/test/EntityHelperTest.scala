//package test
//
//import model.{CompanyHelper, EventHelper}
//import org.joda.time.DateTime
//import org.scalatest.FlatSpec
//
//class EntityHelperTest extends FlatSpec {
//
//  "Event helper" should "help" in {
//    EventHelper.createSchema
//    val e = EventHelper.Event(None, Some("INSERT " + DateTime.now), None)
//    EventHelper.insert(e).await
//    val events = EventHelper.stream.take(1000)
//    events foreach { e =>
//      println(s"E: $e")
//    }
//  }
//
//  "Company helper" should "help" in {
//    CompanyHelper.createSchema
//    val c = CompanyHelper.Company(-1, "Subeli Sp. z o.o.", None)
//    CompanyHelper.insert(c).await
//    val companies = CompanyHelper.stream.take(1000)
//    companies foreach { c =>
//      println(s"C: $c")
//    }
//  }
//}
