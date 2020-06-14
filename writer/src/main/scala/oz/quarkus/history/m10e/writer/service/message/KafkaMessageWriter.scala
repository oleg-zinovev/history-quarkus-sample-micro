package oz.quarkus.history.m10e.writer.service.message

import java.util.concurrent.CompletionStage

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import org.eclipse.microprofile.reactive.messaging.{Channel, Emitter}
import oz.quarkus.history.m10e.writer.model.{Message, MessageRecord}
import oz.quarkus.history.m10e.writer.utils.HostInfo

/**
 * @author olegzinovev
 * @since 08.06.2020
 **/
@ApplicationScoped
class KafkaMessageWriter @Inject()(@Channel("messages") val emitter: Emitter[MessageRecord]) extends MessageWriter {

  override def append(message: Message): CompletionStage[Void] = {
    val record = MessageRecord(message, HostInfo.hostName)
    emitter.send(record)
  }
}
