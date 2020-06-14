package oz.quarkus.history.m10e.writer.service.message

import java.time.Duration

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import javax.inject.Inject
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.junit.jupiter.api.{Assertions, Test}
import oz.quarkus.history.m10e.writer.KafkaResource
import oz.quarkus.history.m10e.writer.model.Message

import scala.util.Using

/**
 * @author olegzinovev
 * @since 14.06.2020
 **/
@QuarkusTest
@QuarkusTestResource(classOf[KafkaResource])
class KafkaMessageWriterITest {

  @ConfigProperty(name="kafka.bootstrap.servers")
  var kafka: String = _

  @Inject
  var messageWriter: MessageWriter = _

  @Test
  def testWrite(): Unit = {
    messageWriter.append(Message("unknown", "Lorem Ipsum")).toCompletableFuture.get()

    Using(new KafkaConsumer[String, String](
      java.util.Collections.singletonMap[String, Object](CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, kafka),
      new StringDeserializer,
      new StringDeserializer)) { consumer =>
      consumer.subscribe(java.util.Collections.singleton("messages"))
      consumer.seekToBeginning(java.util.Collections.emptyList())
      val messages = consumer.poll(Duration.ofMillis(1000))
      Assertions.assertEquals(1, messages.count())
    }

  }
}
