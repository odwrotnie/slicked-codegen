package slicked

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

object SlickedDatabaseConfig {

  lazy val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("model")
  lazy val dbConfigProfile = dbConfig.profile
}

trait SlickedDatabaseConfig
  extends HasDatabaseProfile
    with HasDatabaseInstance {

  val profile = dbConfigProfile

  lazy val dbConfig: DatabaseConfig[JdbcProfile] = SlickedDatabaseConfig.dbConfig
  lazy val dbConfigProfile = SlickedDatabaseConfig.dbConfigProfile
  lazy val db: profile.api.Database = dbConfig.db

  require(profile.getClass == dbConfigProfile.getClass, "Should never happen")
}
