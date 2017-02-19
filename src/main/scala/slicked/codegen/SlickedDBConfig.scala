package slicked.codegen

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.JdbcProfile

trait SlickedDBConfig
  extends LazyLogging {

  def CONFIG_ROOT = "model"

  lazy val conf: Config = ConfigFactory.load.getConfig(CONFIG_ROOT)

  lazy val slickProfileString: String = conf.getString("profile")
  lazy val slickProfile: JdbcProfile = slickProfileString match {
    case "slick.jdbc.H2Profile" => slick.jdbc.H2Profile
    case "slick.jdbc.MySQLProfile" => slick.jdbc.MySQLProfile
  }

  import slickProfile.api._
  lazy val db: Database = Database.forConfig("db", conf)

  lazy val keepAliveConnection = true
}

object SlickedDBConfig
  extends SlickedDBConfig
