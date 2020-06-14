package oz.quarkus.history.m10e.writer.service.message

import java.util.concurrent.CompletableFuture
import java.util.concurrent.atomic.AtomicInteger

import org.eclipse.microprofile.reactive.messaging.Emitter
import org.junit.jupiter.api.{Assertions, Test}
import org.mockito.{Matchers, Mockito}
import oz.quarkus.history.m10e.writer.model.{Message, MessageRecord}

/**
 * @author olegzinovev
 * @since 14.06.2020
 **/
class KafkaMessageWriterTest {

  @Test
  def testWrite(): Unit = {
    val emitter = Mockito.mock(classOf[Emitter[MessageRecord]])
    Mockito.when(emitter.send(Matchers.any[MessageRecord]()))
      .thenReturn(CompletableFuture.completedStage(null))
    val written = new AtomicInteger()
    val writer = new KafkaMessageWriter(emitter)
    writer.append(Message("unknown", "Lorem Ipsum")).thenAccept(_ => written.incrementAndGet())

    Assertions.assertEquals(1, written.get())
  }

  @Test
  def testWriteError(): Unit = {
    val error = new RuntimeException()
    val emitter = Mockito.mock(classOf[Emitter[MessageRecord]])
    Mockito.when(emitter.send(Matchers.any[MessageRecord]()))
      .thenReturn(CompletableFuture.failedStage(error))

    var throwable: Throwable = null
    val writer = new KafkaMessageWriter(emitter)
    writer.append(Message("unknown", "Lorem Ipsum")).handle((_, t) => throwable = t)

    Assertions.assertEquals(error, throwable)
  }
}