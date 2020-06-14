package oz.quarkus.history.m10e.writer

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.{KafkaContainer, Network}
import java.util.Collections

import org.testcontainers.containers.wait.strategy.Wait

class KafkaResource extends QuarkusTestResourceLifecycleManager {
  val kafkaContainer: KafkaContainer = new KafkaContainer().withEmbeddedZookeeper()
    .withNetwork(Network.SHARED)
    .withEnv("auto.create.topics.enable", "true")
    .withExposedPorts(9092, 9093)
    .waitingFor(Wait.forListeningPort())

  override def start(): java.util.Map[String, String] = {
    kafkaContainer.start()
    val m = Collections.singletonMap("kafka.bootstrap.servers", kafkaContainer.getBootstrapServers)
    m
  }

  override def stop(): Unit = {
    kafkaContainer.stop()
  }
}