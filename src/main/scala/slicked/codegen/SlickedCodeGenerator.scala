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

  // override def url: String = "jdbc:h2:./example"
  // override def url: String = "jdbc:mysql://subeli.com:3306/slick?user=slick&password=slickslick1234"
  override def url: String = "jdbc:mysql://subeli.com:3306/forex?user=krd&password=xaij5Es5"

  val DRIVER: JdbcProfile = slickProfile
  val PACKAGE_NAME = "slicked.model"
  val CONTAINER_NAME = "Tables"
  val FILE_NAME = s"$CONTAINER_NAME.scala"

  def main(args: Array[String]): Unit = {
    logger.info(s"Generating model classes from $url in $PACKAGE_NAME.$CONTAINER_NAME")

    val writeToFileFuture = codegen map { scg =>

      scg.tablesByName.keys foreach { key =>
        logger.info(s"Generated table: ${ key.table }")
      }

      scg.writeToFile(
        slickProfileString,
        s"_GENERATED/src/main/scala/${ PACKAGE_NAME.split(".").mkString("/") }",
        PACKAGE_NAME,
        CONTAINER_NAME,
        FILE_NAME
      )
    }

    Await.result(writeToFileFuture, 1.minute)

    logger.info(s"Generated model classes in $FILE_NAME")
  }

  val db: DRIVER.backend.DatabaseDef = DRIVER.api.Database.forURL(url, driver = jdbcDriver)

  // Filter out desired tables
  val included = Seq("COFFEES","SUPPLIERS","COF_INVENTORY")
  def filterTable(t: MTable): Boolean = true //included contains t.name.name

  val codegen: Future[SourceCodeGenerator] = db.run {
    DRIVER.defaultTables.map(_.filter(t => filterTable(t))).flatMap( DRIVER.createModelBuilder(_,false).buildModel )
  } map { model => new SlickedSourceCodeGenerator((model)) }
}
