package oz.quarkus.history.m10e.writer.resources

import javax.inject.Inject
import javax.ws.rs.{GET, Path, Produces}
import oz.quarkus.history.m10e.writer.service.hello.HelloService

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
@Path("/hello")
@Produces(Array("text/plain"))
class HelloResource @Inject()(val service: HelloService) {

  def this() = this(null)

  @GET
  def get(): String = service.hello()
}
