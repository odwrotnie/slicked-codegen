package slicked.codegen

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait SlickedDBConfig
  extends LazyLogging {

  def CONFIG_ROOT = "model"

  lazy val conf: Config = ConfigFactory.load.getConfig(CONFIG_ROOT)
  lazy val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile](CONFIG_ROOT)
  lazy val db: dbConfig.profile.api.Database = dbConfig.db

  lazy val slickProfileString: String = dbConfig.profileName //profile.getClass.getName.toString.dropRight(1) // TODO "slicked.codegen.SlickedDBConfig.profile"
  //lazy val profile: JdbcProfile = dbConfig.profile

  lazy val keepAliveConnection = true
}

object SlickedDBConfig
  extends SlickedDBConfig
