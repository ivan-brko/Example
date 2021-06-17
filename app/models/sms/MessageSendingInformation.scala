package models.sms

import play.api.libs.json.{ Format, Json }

final case class MessageSendingInformation(
    transactionId: String
)

object MessageSendingInformation {
  implicit lazy val jsonFormat: Format[MessageSendingInformation] = Json.format[MessageSendingInformation]
}
