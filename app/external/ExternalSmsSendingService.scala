package external

import javax.inject.{ Inject, Singleton }
import scala.concurrent.Future

case class ExternalSmsMessage(
    country: String,
    recipient: String,
    contents: String,
    allowRoaming: Boolean
)

case class ExternalSmsMessageResponse(
    externalSmsMessage: ExternalSmsMessage,
    messageHandlingId: Option[String]
)

@Singleton
class ExternalSmsSendingService @Inject() () {

  //placeholder impl
  def sendMessage(message: ExternalSmsMessage): Future[ExternalSmsMessageResponse] = {
    println(s"Sending an sms message $message...")
    Future.successful(ExternalSmsMessageResponse(externalSmsMessage = message, messageHandlingId = Some("TID_12345")))
  }
}
