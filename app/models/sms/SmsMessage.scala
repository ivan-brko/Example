package models.sms

import play.api.libs.json.{ Format, Json }

final case class SmsMessage(
    recipient: String,
    contents: String
)

object SmsMessage {
  implicit lazy val jsonFormat: Format[SmsMessage] = Json.format[SmsMessage]
}
