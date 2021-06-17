package routers

import play.api.routing.SimpleRouter
import play.api.routing.Router
import utils.EndpointInterpreter._
import akka.stream.Materializer
import scala.concurrent.ExecutionContext
import javax.inject.{ Inject, Singleton }
import service.message.sms.SmsMessageService
import endpoints.sms.SmsMessageEndpoints
import utils.AppConfig

@Singleton
class MessageRouter @Inject() (
    smsMessageService: SmsMessageService,
    appConfig: AppConfig
)(implicit
    val mat: Materializer,
    ec: ExecutionContext
) extends SimpleRouter {

  override def routes: Router.Routes =
    SmsMessageEndpoints.sendMessage.interpretRoute(appConfig.runtime) { message =>
      smsMessageService.sendMessage(message).mapError(_ => ())
    }
}
