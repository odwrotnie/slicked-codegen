package hello

import com.typesafe.scalalogging.LazyLogging

object Hello
  extends LazyLogging {

  def main(args: Array[String]) = {
    logger.info("Hello!")
  }
}
