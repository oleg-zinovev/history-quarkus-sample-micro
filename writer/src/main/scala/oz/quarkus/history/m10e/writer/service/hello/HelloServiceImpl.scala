package oz.quarkus.history.m10e.writer.service.hello

import java.net.InetAddress

import javax.enterprise.context.ApplicationScoped
import oz.quarkus.history.m10e.writer.utils.HostInfo

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
@ApplicationScoped
class HelloServiceImpl extends HelloService {
  override def hello(): String = {
    val name = HostInfo.hostName
    s"Hello from $name"
  }
}
