package oz.quarkus.history.m10e.writer.service.message

import java.util.concurrent.CompletionStage

import oz.quarkus.history.m10e.writer.model.Message

/**
 * @author olegzinovev
 * @since 08.06.2020
 **/
trait MessageWriter {
  def append(message: Message): CompletionStage[Void]
}
