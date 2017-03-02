package slicked

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slicked.codegen.SlickedCodeGenerator

object SlickedDatabaseConfig {

  lazy val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile](SlickedCodeGenerator.CONFIG_ROOT)
  lazy val profile: JdbcProfile = dbConfig.profile
}

trait SlickedDatabaseConfig
  extends DatabaseProfile {

  lazy val profile: JdbcProfile = SlickedDatabaseConfig.profile
  lazy val dbConfig: DatabaseConfig[JdbcProfile] = SlickedDatabaseConfig.dbConfig
  lazy val db: profile.api.Database = SlickedDatabaseConfig.dbConfig.db
}
