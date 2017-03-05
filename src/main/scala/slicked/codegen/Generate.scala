package slicked.codegen

import com.typesafe.config.Config
import rzepaw.configuration.Configuration
import slicked.SlickedDatabaseConfig

object Generate
  extends App
  with Configuration {

  val genConf = configuration.getConfig("model.generator")

  val packageName: String = genConf.getString("package")
  val containerName: String = genConf.getString("object")
  val fileName: String = s"$containerName.scala"
  val folderPath: String = s"src/main/scala/${ packageName.split(".").mkString("/") }"
  val tableFilterRegex: String = genConf.getString("tableFilterRegex")

  val scg = SlickedCodeGenerator(packageName, containerName, fileName, folderPath, tableFilterRegex)

  scg.generate
}
