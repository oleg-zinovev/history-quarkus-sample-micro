package oz.quarkus.history.m10e.writer.resources

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import oz.quarkus.history.m10e.writer.service.hello.HelloServiceImpl

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
@QuarkusTest
class HelloResourceTest {
  @Test
  def testHelloEndpoint(): Unit = {
    RestAssured.given()
      .`when`().get("/hello")
      .`then`()
      .statusCode(200)
      .body(CoreMatchers`is` "Hello from Mockito")
  }
}

object HelloResourceTest {

  import io.quarkus.test.junit.QuarkusMock
  import org.junit.jupiter.api.BeforeAll

  @BeforeAll def setup(): Unit = {
    val mock = Mockito.mock(classOf[HelloServiceImpl])
    Mockito.when(mock.hello()).thenReturn("Hello from Mockito")
    QuarkusMock.installMockForType(mock, classOf[HelloServiceImpl])
  }
}
