package slicked.helpers.server

import org.hsqldb.Server
import org.hsqldb.persist.HsqlProperties

/**
  * URL: jdbc:hsqldb:hsql:<PATH>
  */
case class HSQLDBServer(path: String = "./db", dbName: String = "db") {

  val props: HsqlProperties = new HsqlProperties
  props.setProperty("server.database.0", path)
  props.setProperty("server.dbname.0", dbName)
  val server: Server = new Server

  server.setProperties(props)

  server.start()
}
