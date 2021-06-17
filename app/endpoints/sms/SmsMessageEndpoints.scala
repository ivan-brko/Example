package endpoints.sms

import sttp.tapir._
import sttp.tapir.server.play.PlayServerInterpreter
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import akka.stream.Materializer
import play.api.routing.Router.Routes
import models.sms.SmsMessage
import sttp.tapir.generic.auto._
import sttp.tapir.json.play._
import models.sms.MessageSendingInformation

object SmsMessageEndpoints {

  val sendMessage: Endpoint[SmsMessage, Unit, MessageSendingInformation, Any] =
    endpoint.post
      .in("sms" / "send")
      .in(jsonBody[SmsMessage])
      .out(jsonBody[MessageSendingInformation])
}
