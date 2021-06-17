package routers

import play.api.routing.SimpleRouter
import play.api.routing.Router
import utils.EndpointInterpreter._
import akka.stream.Materializer
import scala.concurrent.ExecutionContext
import javax.inject.{ Inject, Singleton }
import service.message.sms.SmsMessageService
import endpoints.status.StatusEndpoints
import utils.AppConfig
import domain.status.domain.status.MessageStatusDomain
import models.status.MessageStatusData
import domain.status.MessageStatus

@Singleton
class StatusRouter @Inject() (
    messageStatusDomain: MessageStatusDomain,
    appConfig: AppConfig
)(implicit
    val mat: Materializer,
    ec: ExecutionContext
) extends SimpleRouter {

  override def routes: Router.Routes =
    StatusEndpoints.getMessageStatus
      .interpretRoute(appConfig.runtime) { id =>
        messageStatusDomain
          .getMessageStatus(id)
          .map(status => MessageStatusData(id, status.toString()))
          .mapError(_ => ())
      }
      .orElse(
        StatusEndpoints.setMessageStatus.interpretRoute(appConfig.runtime) { incomingStatus =>
          val status = incomingStatus.messageStatus match {
            case "FAIL"                 => MessageStatus.FAIL
            case "WAITING_CONFIRMATION" => MessageStatus.WAITING_CONFIRMATION
            case "SUCCESS"              => MessageStatus.SUCCESS
            case _                      => MessageStatus.FAIL
          }
          messageStatusDomain
            .storeMessageStatus(incomingStatus.messageId, status)
            .map(status => MessageStatusData(incomingStatus.messageId, status.toString()))
            .mapError(_ => ())
        // .tapBoth()
        }
      )
}
