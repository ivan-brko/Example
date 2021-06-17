package utils

import javax.inject.{ Inject, Singleton }

@Singleton
class AppConfig @Inject() () {
  lazy val runtime = zio.Runtime.default
}
