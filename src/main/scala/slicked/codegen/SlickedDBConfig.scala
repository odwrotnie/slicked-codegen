package slicked.codegen

import java.io.FileInputStream
import java.util.Properties

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.JdbcProfile

trait SlickedDBConfig
  extends LazyLogging {

  lazy val conf = ConfigFactory.load.getConfig("database")

//  lazy val jdbcDriver: String = conf.getString("database.db.properties.driver")
  lazy val slickProfileString: String = conf.getString("profile")
  lazy val slickProfile: JdbcProfile = slickProfileString match {
    case "slick.jdbc.H2Profile" => slick.jdbc.H2Profile
    case "slick.jdbc.MySQLProfile" => slick.jdbc.MySQLProfile
  }
//  lazy val server = conf.getString("database.db.properties.serverName")
//  lazy val database = conf.getString("database.db.properties.databaseName")
//  lazy val user = conf.getString("database.db.properties.user")
//  lazy val password = conf.getString("database.db.properties.password")

  import slickProfile.api._
  lazy val db = Database.forConfig("db", conf)

  // logger.info(s"DB profile: $slickProfile, server: $server, database: $database, user: $user, password: $password")

  lazy val keepAliveConnection = true
}
