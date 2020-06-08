package oz.quarkus.history.m10e.writer.model

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import org.junit.jupiter.api.{Assertions, Test}

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
class MessageSerDeTest {
  @Test
  def testSerialization(): Unit = {
    val mapper = new ObjectMapper()
      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

    val message = Message("unknown", "Lorem ipsum")

    val serialized = mapper.writeValueAsString(message)
    Assertions.assertEquals("""{"author":"unknown","text":"Lorem ipsum"}""", serialized)
  }

  @Test
  def testDeserialization(): Unit = {
    val mapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val message = mapper.readValue("""{"author":"unknown","text":"Lorem ipsum"}""", classOf[Message])
    Assertions.assertEquals("unknown", message.author)
    Assertions.assertEquals("Lorem ipsum", message.text)
  }
}
