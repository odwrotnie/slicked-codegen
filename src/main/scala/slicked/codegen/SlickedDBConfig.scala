package slicked.codegen

import java.io.FileInputStream
import java.util.Properties

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.JdbcProfile

trait SlickedDBConfig
  extends LazyLogging {

  lazy val conf = ConfigFactory.load
  lazy val profile = conf.getString("tsql.profile")
  lazy val server = conf.getString("tsql.db.properties.serverName")
  lazy val database = conf.getString("tsql.db.properties.databaseName")
  lazy val user = conf.getString("tsql.db.properties.user")
  lazy val password = conf.getString("tsql.db.properties.password")

  logger.info(s"DB profile: $profile, server: $server, database: $database, user: $user, password: $password")

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
