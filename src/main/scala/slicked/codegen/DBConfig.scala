package slicked.codegen

import slick.jdbc.JdbcProfile

trait DBConfig {

  lazy val url = "jdbc:h2:./example" //;DB_CLOSE_DELAY=-1"

  lazy val jdbcDriver =  "org.h2.Driver"
  lazy val slickProfile = slick.jdbc.H2Profile
  lazy val slickProfileString = "slick.driver.H2Driver"
  lazy val keepAliveConnection = true
}

//trait DBConfig {
//  // connection info for a pre-populated throw-away, in-memory db for this demo, which is freshly initialized on every run
//  val initScripts = Seq(
//    "drop-tables.sql",
//    "create-tables.sql",
//    "populate-tables.sql")
//
//  private lazy val urlRaw = "jdbc:mysql://subeli.com:3306/slick?user=slick&password=slickslick1234&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&maxReconnects=10"
//  private lazy val initScript: String = initScripts.map(fileName => s"runscript from 'src/sql/$fileName'").mkString("\\;")
//  private lazy val urlWithInitScript = s"$urlRaw;INIT=$initScript"
//  lazy val url = urlRaw
//
//  lazy val jdbcDriver: String = "com.mysql.jdbc.Driver"
//  lazy val slickProfile: JdbcProfile = slick.jdbc.MySQLProfile
//  lazy val slickProfileString: String = slickProfile.getClass.getName.replace("$", "") //"slick.jdbc.MySQLProfile"
//  lazy val keepAliveConnection = true
//}
