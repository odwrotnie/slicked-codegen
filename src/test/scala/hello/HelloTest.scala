package hello

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.FlatSpec
import slicked.codegen.SlickedCodeGenerator

class HelloTest
  extends FlatSpec
  with LazyLogging {

  "2 plus 2" should "equal 4" in {
    SlickedCodeGenerator.generate
  }
}
