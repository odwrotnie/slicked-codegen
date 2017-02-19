package slicked.codegen

import java.io.FileInputStream
import java.util.Properties

import slick.jdbc.JdbcProfile

trait DBConfig {

//  import scala.io.Source
//  Source.fromResource("readme.txt").getLines
//
//  val prop = new Properties()
//  prop.load(getClass().getResourceAsStream("slicked-db.properties"))

  /**
    * Tested with:
    * - jdbc:h2:./example
    * - jdbc:mysql://subeli.com:3306/slick?user=slick&password=slickslick1234
    * @return
    */
  def url: String

  lazy val (jdbcDriver, slickProfile): (String, slick.jdbc.JdbcProfile) = url.split(":").toList match {
    case "jdbc" :: "h2" :: tail => ("org.h2.Driver", slick.jdbc.H2Profile)
    case "jdbc" :: "mysql" :: tail => ("com.mysql.jdbc.Driver", slick.jdbc.MySQLProfile)
  }

  lazy val slickProfileString = slickProfile.getClass.toString.replace("$", "")
  lazy val keepAliveConnection = true
}
