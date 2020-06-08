package oz.quarkus.history.m10e.writer.model

import com.fasterxml.jackson.annotation.JsonProperty

import scala.beans.BeanProperty

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
final case class Message(@BeanProperty @JsonProperty(value = "author", required = true) author: String,
                         @BeanProperty @JsonProperty(value = "text", required = true) text: String)
