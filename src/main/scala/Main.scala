import cats.effect.{ExitCode, IO, IOApp}
import config.AppConfig
import fs2.Stream
object Main extends  IOApp {
  
  override def run(args: List[String]): IO[ExitCode] = (for {
    appConfig <- Stream.eval(AppConfig.config.load)
    server<- Server.stream(appConfig)
  } yield server).compile.drain.as(ExitCode.Success)


}
    