package oz.quarkus.history.m10e.writer.resources

import java.util.concurrent.CompletionStage

import io.smallrye.mutiny.Uni
import javax.inject.Inject
import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.{Consumes, POST, Path, Produces}
import oz.quarkus.history.m10e.writer.model.Message
import oz.quarkus.history.m10e.writer.service.message.MessageWriter

/**
 * @author olegzinovev
 * @since 14.06.2020
 **/
@Path("/message")
@Produces(Array("text/plain"))
class MessageResource @Inject()(val service: MessageWriter) {

  def this() = this(null)

  @POST
  @Consumes(Array("application/json"))
  def post(message: Message): CompletionStage[Response] = {
    service.append(message).handle((_, error) => {
      if (error == null) {
        Response.noContent().build()
      } else {
        Response.status(Response.Status.BAD_REQUEST).entity(error.getMessage).`type`(MediaType.TEXT_PLAIN).build()
      }
    })
  }
}