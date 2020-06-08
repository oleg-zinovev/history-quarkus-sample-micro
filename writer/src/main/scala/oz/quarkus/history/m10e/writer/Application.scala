package oz.quarkus.history.m10e.writer

import io.quarkus.runtime.{Quarkus, QuarkusApplication}
import io.quarkus.runtime.annotations.QuarkusMain
import org.slf4j.LoggerFactory

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
@QuarkusMain()
class Application extends QuarkusApplication {
  private val logger = LoggerFactory.getLogger(classOf[Application])

  override def run(args: String*): Int = {
    logger.info("Starting Quarkus Sample History Writer service")
    Quarkus.waitForExit()
    0
  }
}
