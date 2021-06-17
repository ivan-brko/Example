package service.message.sms

import models.sms.SmsMessage
import zio.Task
import javax.inject.{ Inject, Singleton }
import models.sms.MessageSendingInformation
import external._

@Singleton
class SmsMessageService @Inject() (externalSmsSendingService: ExternalSmsSendingService) {

  def sendMessage(message: SmsMessage): Task[MessageSendingInformation] =
    for {
      receivedData  <-
        Task.fromFuture(_ =>
          externalSmsSendingService.sendMessage(ExternalSmsMessage("US", message.recipient, message.contents, false))
        )
      transactionId <- Task.getOrFail(receivedData.messageHandlingId)
    } yield MessageSendingInformation(transactionId)
}
