package oz.quarkus.history.m10e.writer.service.hello

import java.net.InetAddress

import io.quarkus.test.junit.QuarkusTest
import javax.inject.Inject
import org.junit.jupiter.api.{Assertions, Test}

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
@QuarkusTest
class HelloServiceITest {
  @Inject
  private var service: HelloService = _

  @Test
  def test(): Unit = {
    val hello = service.hello()

    val name = InetAddress.getLocalHost.getHostName
    Assertions.assertEquals(s"Hello from $name", hello)
  }
}
