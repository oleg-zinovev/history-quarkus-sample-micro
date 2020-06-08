package oz.quarkus.history.m10e.writer.utils

import java.net.InetAddress

/**
 * @author olegzinovev
 * @since 08.06.2020
 **/
object HostInfo {
  lazy val hostName: String = InetAddress.getLocalHost.getHostName
}
