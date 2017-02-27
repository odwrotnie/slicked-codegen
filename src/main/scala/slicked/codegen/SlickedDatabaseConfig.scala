package slicked.codegen

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

object SlickedDatabaseConfig {

  lazy val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile](SlickedCodeGenerator.CONFIG_ROOT)
  lazy val profile: JdbcProfile = dbConfig.profile
}

trait SlickedDatabaseConfig
  extends LazyLogging {

  lazy val profile: JdbcProfile = SlickedDatabaseConfig.profile
  lazy val dbConfig: DatabaseConfig[JdbcProfile] = SlickedDatabaseConfig.dbConfig
  lazy val db: profile.api.Database = SlickedDatabaseConfig.dbConfig.db
}
