package oz.quarkus.history.m10e.writer.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.{Assertions, Test}

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
class MessageRecordSerDeTest {
  @Test
  def testSerialization(): Unit = {
    val mapper = new ObjectMapper()
      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .registerModule(new JavaTimeModule)

    val message = MessageRecord("unknown", "Lorem ipsum", "node-1", LocalDateTime.MIN)

    val serialized = mapper.writeValueAsString(message)
    Assertions.assertEquals(s"""{"author":"unknown","text":"Lorem ipsum","node":"node-1","date":"${LocalDateTime.MIN.toISO}"}""", serialized)
  }

  @Test
  def testDeserialization(): Unit = {
    val mapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .registerModule(new JavaTimeModule)
    val message = mapper.readValue(s"""{"author":"unknown","text":"Lorem ipsum","node":"node-1","date":"${LocalDateTime.MIN.toISO}"}""", classOf[MessageRecord])
    Assertions.assertEquals("unknown", message.author)
    Assertions.assertEquals("Lorem ipsum", message.text)
    Assertions.assertEquals("node-1", message.node)
    Assertions.assertEquals(LocalDateTime.MIN, message.date)
  }

  implicit class ExtendedLocalDateTime(ldt: LocalDateTime) {
    def toISO: String = {
      DateTimeFormatter.ISO_DATE_TIME.format(ldt)
    }
  }
}
