package oz.quarkus.history.m10e.writer.service.hello

import java.net.InetAddress

import javax.enterprise.context.ApplicationScoped

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
@ApplicationScoped
class HelloServiceImpl extends HelloService {
  override def hello(): String = {
    val name = InetAddress.getLocalHost.getHostName
    s"Hello from $name"
  }
}
