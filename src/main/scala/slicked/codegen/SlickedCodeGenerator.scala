package slicked.codegen

import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging
import slick.codegen.SourceCodeGenerator
import slick.jdbc.JdbcBackend.DatabaseDef
import slick.jdbc.JdbcProfile
import slick.jdbc.meta.MTable
import slicked.SlickedDatabaseConfig

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.util.matching.Regex

object SlickedCodeGenerator
  extends SlickedDatabaseConfig
    with LazyLogging {

  def CONFIG_ROOT = "model"
  val conf: Config = ConfigFactory.load.getConfig(CONFIG_ROOT)
  val genConf = conf.getConfig("generator")

  val PACKAGE_NAME: String = genConf.getString("package")
  val CONTAINER_NAME: String = genConf.getString("object")
  val FILE_NAME: String = s"$CONTAINER_NAME.scala"
  val FOLDER: String = s"src/main/scala/${ PACKAGE_NAME.split(".").mkString("/") }"

  def generate: Unit = {
    val writeToFileFuture = codegen map { scg =>
      scg.tablesByName.keys foreach { key =>
        logger.info(s"Generated table: ${ key.table }")
      }
      scg.writeToFile(
        dbConfig.profileName,
        FOLDER,
        PACKAGE_NAME,
        CONTAINER_NAME,
        FILE_NAME)
    }
    Await.result(writeToFileFuture, 5.minutes)
    logger.info(s"Generated model classes in $FILE_NAME")
  }

  import profile._

  // Filter out desired tables
  val tableFilterRegex: String = genConf.getString("tableFilterRegex")
  def filterTable(t: MTable): Boolean =  t.name.name.toLowerCase().matches(tableFilterRegex)

  val codegen: Future[SourceCodeGenerator] = db.run {
    profile.defaultTables.map(_.filter(t => filterTable(t))).flatMap( profile.createModelBuilder(_,false).buildModel )
  } map { model => new SlickedSourceCodeGenerator(model) }
}
