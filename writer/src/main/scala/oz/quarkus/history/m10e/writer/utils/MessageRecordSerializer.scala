package oz.quarkus.history.m10e.writer.utils

import org.apache.kafka.common.serialization.Serializer
import oz.quarkus.history.m10e.writer.model.MessageRecord

/**
 * @author olegzinovev
 * @since 08.06.2020
 **/
class MessageRecordSerializer extends Serializer[MessageRecord] {
  override def serialize(topic: String, data: MessageRecord): Array[Byte] = {
    Array()
  }
}
