package slicked.codegen

import java.io.FileInputStream
import java.util.Properties

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.JdbcProfile

trait SlickedDBConfig
  extends LazyLogging {

  lazy val conf = ConfigFactory.load
  lazy val jdbcDriver: String = conf.getString("tsql.db.properties.driver")
  lazy val slickProfileString: String = conf.getString("tsql.profile")
  lazy val slickProfile: JdbcProfile = slickProfileString match {
    case "slick.jdbc.H2Profile" => slick.jdbc.H2Profile
  }
  lazy val server = conf.getString("tsql.db.properties.serverName")
  lazy val database = conf.getString("tsql.db.properties.databaseName")
  lazy val user = conf.getString("tsql.db.properties.user")
  lazy val password = conf.getString("tsql.db.properties.password")

  // logger.info(s"DB profile: $slickProfile, server: $server, database: $database, user: $user, password: $password")

  lazy val keepAliveConnection = true
}
