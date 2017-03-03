package slicked

import rzepaw.configuration.Configuration
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

object SlickedDatabaseConfig
  extends Configuration {

  def MODEL_CONFIG_ROOT = "model"

  lazy val dbConfig: DatabaseConfig[JdbcProfile] =
    DatabaseConfig.forConfig[JdbcProfile](MODEL_CONFIG_ROOT, configuration)
  lazy val profile: JdbcProfile = dbConfig.profile
}

trait SlickedDatabaseConfig
  extends DatabaseProfile {

  lazy val profile: JdbcProfile = SlickedDatabaseConfig.profile
  lazy val dbConfig: DatabaseConfig[JdbcProfile] = SlickedDatabaseConfig.dbConfig
  lazy val db: profile.api.Database = SlickedDatabaseConfig.dbConfig.db
}
