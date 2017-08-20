package slicked

import slick.basic.DatabaseConfig
import slick.jdbc.{H2Profile, JdbcProfile}

object SlickedDatabaseConfig {

  lazy val dbConfig: DatabaseConfig[JdbcProfile] =
    DatabaseConfig.forConfig[JdbcProfile]("model")
  lazy val profile: JdbcProfile = dbConfig.profile
}

trait SlickedDatabaseConfig
  extends DatabaseProfile {

  lazy val profile: JdbcProfile = SlickedDatabaseConfig.profile
  lazy val dbConfig: DatabaseConfig[JdbcProfile] = SlickedDatabaseConfig.dbConfig
  lazy val db: profile.api.Database = SlickedDatabaseConfig.dbConfig.db

  if (profile.isInstanceOf[H2Profile]) {
    // URL: jdbc:h2:tcp://localhost/mem:db
    org.h2.tools.Server.createTcpServer("-tcpAllowOthers").start()
  }
}

//trait SlickedDatabaseConfigFromTables
//  extends DatabaseProfile {
//
//  lazy val tables: { def profile: JdbcProfile }
//  lazy val profile: JdbcProfile = tables.profile
//  lazy val dbConfig: DatabaseConfig[JdbcProfile] = SlickedDatabaseConfig.dbConfig
//  lazy val db: profile.api.Database = SlickedDatabaseConfig.dbConfig.db
//
//  if (profile.isInstanceOf[H2Profile]) {
//    // URL: jdbc:h2:tcp://localhost/mem:db
//    org.h2.tools.Server.createTcpServer("-tcpAllowOthers").start()
//  }
//}
