package slicked.helpers.server

case class H2Server() {

  val w = org.h2.tools.Server
    .createWebServer("-tcpAllowOthers")
    .start()

  val s = org.h2.tools.Server
    .createTcpServer("-tcpAllowOthers", "-tcpPort", "35853")
    .start()

  while (true) {
    println(s"H2: ${s.getStatus} / web: ${w.getStatus}")
    Thread.sleep(10000)
  }
}
