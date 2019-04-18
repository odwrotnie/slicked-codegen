package test

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.FlatSpec
import slicked.codegen.SlickedCodeGenerator

class GeneratorTest extends FlatSpec with LazyLogging {

  "Generator" should "generate" in {

    val conf = ConfigFactory.load

    val packageName: String = conf.getString("generator.package")
    val containerName: String = conf.getString("generator.object")
    val fileName: String = s"$containerName.scala"
    val folderPath: String = s"src/test/scala/${packageName.split(".").mkString("/")}"
    val tableFilterRegex: String = conf.getString("generator.tableFilterRegex")

    val scg = SlickedCodeGenerator(packageName,
                                   containerName,
                                   fileName,
                                   folderPath,
                                   tableFilterRegex)

    scg.generate
  }
}
