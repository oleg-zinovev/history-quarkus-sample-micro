package oz.quarkus.history.m10e.writer.config

import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.quarkus.jackson.ObjectMapperCustomizer

/**
 * @author olegzinovev
 * @since 08.06.2020
 **/
class ObjectMapperConfig extends ObjectMapperCustomizer {
  override def customize(objectMapper: ObjectMapper): Unit = {
    objectMapper
      .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
      .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
      .registerModule(new JavaTimeModule)
  }
}
