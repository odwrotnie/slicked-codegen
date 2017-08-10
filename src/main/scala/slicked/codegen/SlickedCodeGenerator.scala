package slicked.codegen

import com.typesafe.scalalogging.LazyLogging
import slick.codegen.SourceCodeGenerator
import slick.jdbc.meta.MTable
import slicked.SlickedDatabaseConfig

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._

case class SlickedCodeGenerator(packageName: String,
                                containerName: String,
                                fileName: String,
                                folder: String,
                                tableFilterRegex: String)
  extends SlickedDatabaseConfig
    with LazyLogging {

  lazy val databaseName = dbConfig.config.getString("db.properties.databaseName")

  logger.info(s"Generating trait $packageName.$containerName" +
    s" from ${ dbConfig.profileName } / ${ dbConfig.config }" +
    s" in $folder${packageName.replace(".", "/")}/$fileName" +
    s" with [$tableFilterRegex] filter")

  def generate: Unit = {
    val writeToFileFuture = codegen map { scg =>
      scg.tablesByName.keys foreach { key =>
        logger.info(s"Generated table: ${ key.table }")
      }
      scg.writeToFile(
        dbConfig.profileName,
        folder,
        packageName,
        containerName,
        fileName)
    }
    Await.result(writeToFileFuture, 5.minutes)
    logger.info(s"Generated model classes in $fileName")
  }

  def filterTable(t: MTable): Boolean = t.name.name.toLowerCase().matches(tableFilterRegex)

  val codegen: Future[SourceCodeGenerator] = db.run {
    profile.defaultTables.map(_.filter(t => filterTable(t))).flatMap( profile.createModelBuilder(_,false).buildModel )
  } map { model => new SlickedSourceCodeGenerator(model) }
}
