package oz.quarkus.history.m10e.writer.model

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}

import scala.beans.BeanProperty

/**
 * @author olegzinovev
 * @since 07.06.2020
 **/
case class Message @JsonCreator()(@BeanProperty @JsonProperty(value = "author", required = true) author: String,
                                  @BeanProperty @JsonProperty(value = "text", required = true) text: String)
