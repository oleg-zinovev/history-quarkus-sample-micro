package oz.quarkus.history.m10e.writer.resources

import java.net.InetAddress

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
@QuarkusTest
class HelloResourceTest {
  @Test
  def testHelloEndpoint(): Unit = {
    val name = InetAddress.getLocalHost.getHostName
    RestAssured.given()
      .`when`().get("/hello")
      .`then`()
      .statusCode(200)
      .body(CoreMatchers`is` s"Hello from $name")
  }
}
