package oz.quarkus.history.m10e.writer.service.hello

import java.net.InetAddress

import org.junit.jupiter.api.{Assertions, Test}

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
class HelloServiceTest {

  @Test
  def testHello(): Unit = {
    val service = new HelloServiceImpl
    val hello = service.hello()

    val name = InetAddress.getLocalHost.getHostName
    Assertions.assertEquals(s"Hello from $name", hello)
  }

}
