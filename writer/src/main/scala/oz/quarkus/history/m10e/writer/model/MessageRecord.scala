package oz.quarkus.history.m10e.writer.model

import java.time.LocalDateTime

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}

import scala.beans.BeanProperty

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
case class MessageRecord @JsonCreator()(@BeanProperty @JsonProperty(value = "author", required = true) author: String,
                                        @BeanProperty @JsonProperty(value = "text", required = true) text: String,
                                        @BeanProperty @JsonProperty(value = "node", required = true) node: String,
                                        @BeanProperty @JsonProperty(value = "date", required = true) date: LocalDateTime = LocalDateTime.now())

object MessageRecord {
  def apply(message: Message, node: String): MessageRecord = new MessageRecord(message.author, message.text, node)
}
