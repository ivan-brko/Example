package utils

import akka.stream.Materializer
import play.api.routing.Router.Routes
import sttp.tapir.Endpoint
import sttp.tapir.server.play._
import zio.{ IO, Runtime }

object EndpointInterpreter {

  implicit class Interpreter[I, E, O](e: Endpoint[I, E, O, Any]) {

    def interpretRoute(runtime: Runtime[zio.ZEnv])(
        logic: I => IO[E, O]
    )(implicit mat: Materializer, serverOptions: PlayServerOptions): Routes =
      PlayServerInterpreter.toRoutes(e) { input =>
        runtime.unsafeRunToFuture(logic(input).either)
      }
  }
}
