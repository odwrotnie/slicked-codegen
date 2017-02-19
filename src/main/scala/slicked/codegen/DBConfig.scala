package slicked.codegen

import slick.jdbc.JdbcProfile

trait DBConfig {

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
