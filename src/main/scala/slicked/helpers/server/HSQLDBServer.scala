package slicked.helpers.server

import org.hsqldb.Server
import org.hsqldb.persist.HsqlProperties

/**
  * URL: jdbc:hsqldb:hsql:<PATH>
  */
case class HSQLDBServer()
  extends DBServer {

  lazy val server: Server = {
    val s = new Server
    s.setProperties(props)
    s
  }

  lazy val props: HsqlProperties = {
    val p = new HsqlProperties
    p.setProperty("server.database.0", dbPath)
    p.setProperty("server.dbname.0", dbName)
    p
  }

  override def start: Unit = {
    server.start()
    while (true) {
      println(s"H2: ${server.getAddress} / ${server.getStateDescriptor}")
      Thread.sleep(10000)
    }
  }
}
