package slicked.codegen

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import slick.codegen.SourceCodeGenerator
import slick.jdbc.JdbcBackend.DatabaseDef
import slick.jdbc.JdbcProfile
import slick.jdbc.meta.MTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

object SlickedCodeGenerator
  extends SlickedDBConfig
    with LazyLogging {

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
        slickProfileString,
        FOLDER,
        PACKAGE_NAME,
        CONTAINER_NAME,
        FILE_NAME)
    }
    Await.result(writeToFileFuture, 1.minute)
    logger.info(s"Generated model classes in $FILE_NAME")
  }

  import dbConfig.profile._

  // Filter out desired tables
  val included = Seq("COFFEES","SUPPLIERS","COF_INVENTORY")
  def filterTable(t: MTable): Boolean = true //included contains t.name.name

  val codegen: Future[SourceCodeGenerator] = db.run {
    profile.defaultTables.map(_.filter(t => filterTable(t))).flatMap( profile.createModelBuilder(_,false).buildModel )
  } map { model => new SlickedSourceCodeGenerator(model) }
}
