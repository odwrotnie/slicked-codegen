package slicked.codegen

import com.typesafe.config._
import com.typesafe.scalalogging.LazyLogging
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

object SlickedDatabaseConfig {

  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile](SlickedCodeGenerator.CONFIG_ROOT)
  val profile: JdbcProfile = dbConfig.profile
}

trait SlickedDatabaseConfig
  extends LazyLogging {

  val profile: JdbcProfile = SlickedDatabaseConfig.profile
  val dbConfig: DatabaseConfig[JdbcProfile] = SlickedDatabaseConfig.dbConfig
  val db: profile.api.Database = SlickedDatabaseConfig.dbConfig.db
  def slickProfileString: String = dbConfig.profileName
}
