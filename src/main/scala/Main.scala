import cats.effect.{ExitCode, IO, IOApp}
import fs2.Stream

import config.AppConfig
import org.http4s.server.middleware.GZip
import org.http4s.server.AuthMiddleware

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = (for {
    appConfig <- Stream.eval(AppConfig.config[IO].load)
    _         <- Server.stream(appConfig)
  } yield ()).compile.drain.as(ExitCode.Success)

}
