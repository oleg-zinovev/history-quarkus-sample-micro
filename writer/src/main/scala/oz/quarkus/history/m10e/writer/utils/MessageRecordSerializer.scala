package oz.quarkus.history.m10e.writer.utils

import io.quarkus.kafka.client.serialization.ObjectMapperSerializer
import oz.quarkus.history.m10e.writer.model.MessageRecord

/**
 * @author olegzinovev
 * @since 08.06.2020
 **/
class MessageRecordSerializer extends ObjectMapperSerializer[MessageRecord] {

}
