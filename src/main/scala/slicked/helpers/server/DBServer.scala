package slicked.helpers.server

abstract class DBServer {

  def dbPath: String = "./db"
  def dbName: String = "db"

  def start: Unit
}
