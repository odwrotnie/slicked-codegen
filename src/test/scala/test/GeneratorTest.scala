package test

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.FlatSpec
import rzepaw.configuration.Configuration
import slicked.codegen.{Generate, SlickedCodeGenerator}

class GeneratorTest
  extends FlatSpec
  with LazyLogging
  with Configuration {

  "Generator" should "generate" in {
    val genConf = configuration.getConfig("model.generator")

    val packageName: String = genConf.getString("package")
    val containerName: String = genConf.getString("object")
    val fileName: String = s"$containerName.scala"
    val folderPath: String = s"test/main/scala/${ packageName.split(".").mkString("/") }"
    val tableFilterRegex: String = genConf.getString("tableFilterRegex")

    val scg = SlickedCodeGenerator(packageName, containerName, fileName, folderPath, tableFilterRegex)

    scg.generate
  }
}
