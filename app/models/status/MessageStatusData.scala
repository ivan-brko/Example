package models.status

import play.api.libs.json.{ Format, Json }

case class MessageStatusData(
    messageId: String,
    messageStatus: String
)

object MessageStatusData {
  implicit lazy val jsonFormat: Format[MessageStatusData] = Json.format[MessageStatusData]
}
