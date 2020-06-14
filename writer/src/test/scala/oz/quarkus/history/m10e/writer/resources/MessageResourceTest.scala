package oz.quarkus.history.m10e.writer.resources

import java.util.concurrent.CompletableFuture

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.mockito.{Matchers, Mockito}
import oz.quarkus.history.m10e.writer.model.Message
import oz.quarkus.history.m10e.writer.service.message.KafkaMessageWriter

/**
 * @author olegzinovev
 * @since 14.06.2020
 **/
@QuarkusTest
class MessageResourceTest {
  @Test
  def testMessageEndpoint(): Unit = {
    RestAssured.given()
      .contentType(ContentType.JSON)
      .body("""{"author":"unknown","text":"Lorem ipsum"}""")
      .`when`().post("/message")
      .`then`().statusCode(204)
  }

  @Test
  def testMessageEndpoint_error(): Unit = {
    RestAssured.given()
      .contentType(ContentType.JSON)
      .body("""{"author":"error","text":"Lorem ipsum"}""")
      .`when`().post("/message")
      .`then`().statusCode(400).body(CoreMatchers `is` "Some error text")
  }
}

object MessageResourceTest {

  import io.quarkus.test.junit.QuarkusMock
  import org.junit.jupiter.api.BeforeAll

  @BeforeAll def setup(): Unit = {
    val mock = Mockito.mock(classOf[KafkaMessageWriter])
    Mockito.when(mock.append(Matchers.eq(Message("unknown", "Lorem ipsum")))).thenReturn(CompletableFuture.completedStage(null))
    Mockito.when(mock.append(Matchers.eq(Message("error", "Lorem ipsum")))).thenReturn(CompletableFuture.failedStage(new IllegalStateException("Some error text")))
    QuarkusMock.installMockForType(mock, classOf[KafkaMessageWriter])
  }
}
