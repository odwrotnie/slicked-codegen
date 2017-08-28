package slicked.helpers.server

import com.typesafe.scalalogging.LazyLogging

case class H2Server()
  extends DBServer
  with LazyLogging {

  lazy val server = org.h2.tools.Server
    .createTcpServer("-tcpAllowOthers", "-tcpPort", "35853")

  lazy val webServer = org.h2.tools.Server
    .createWebServer("-tcpAllowOthers")

  override def start: Unit = {
    server.start()
    webServer.start()
    while (true) {
      logger.info(s"H2: ${server.getStatus} / web: ${webServer.getStatus}")
      Thread.sleep(10000)
    }
  }
}
