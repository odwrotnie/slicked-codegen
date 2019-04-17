package slicked.codegen

import com.typesafe.config.ConfigFactory

object Generator
  extends App {

  val ROOT = "generator"

  val genConf = ConfigFactory.load()

  val packageName: String = genConf.getString(s"$ROOT.package")
  val containerName: String = genConf.getString(s"$ROOT.object")
  val fileName: String = s"$containerName.scala"
  val folderPath: String = s"src/main/scala/${ packageName.split(".").mkString("/") }"
  val tableFilterRegex: String = genConf.getString(s"$ROOT.tableFilterRegex")

  val scg = SlickedCodeGenerator(packageName, containerName, fileName, folderPath, tableFilterRegex)

  scg.generate
}
