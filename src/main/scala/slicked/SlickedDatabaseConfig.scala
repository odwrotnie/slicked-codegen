package slicked

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

object SlickedDatabaseConfig extends LazyLogging {

  val APPLICATION_CONF_ROOT = ConfigFactory.load.getString("slicked.config") // model by default

  logger.info(s"Database config root: $APPLICATION_CONF_ROOT")

  lazy val dbConfig: DatabaseConfig[JdbcProfile] =
    DatabaseConfig.forConfig[JdbcProfile](APPLICATION_CONF_ROOT)
  lazy val dbConfigProfile = dbConfig.profile
}

trait SlickedDatabaseConfig
    extends HasDatabaseProfile
    with HasDatabaseInstance {

  val profile = dbConfigProfile

  lazy val dbConfig: DatabaseConfig[JdbcProfile] =
    SlickedDatabaseConfig.dbConfig
  lazy val dbConfigProfile = SlickedDatabaseConfig.dbConfigProfile
  lazy val db: profile.api.Database = dbConfig.db

  require(profile.getClass == dbConfigProfile.getClass, "Should never happen")
}
