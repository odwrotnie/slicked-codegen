package test

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.FlatSpec
import slicked.codegen.SlickedCodeGenerator

class GeneratorTest extends FlatSpec with LazyLogging {

  "Generator" should "generate" in {

    val xConf = ConfigFactory.load
    val genConf = xConf.getConfig("model.generator")

    val packageName: String = genConf.getString("package")
    val containerName: String = genConf.getString("object")
    val fileName: String = s"$containerName.scala"
    val folderPath: String =
      s"src/test/scala/${packageName.split(".").mkString("/")}"
    val tableFilterRegex: String = genConf.getString("tableFilterRegex")

    val scg = SlickedCodeGenerator(packageName,
                                   containerName,
                                   fileName,
                                   folderPath,
                                   tableFilterRegex)

    scg.generate
  }
}
