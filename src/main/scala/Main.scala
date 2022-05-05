import Routes.Routes

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.middleware.CORSConfig
import org.http4s.Method
import org.http4s.Http
import org.http4s.server.middleware.CORS
import org.http4s.server.middleware.HSTS
import cats.data.Kleisli
import org.http4s.HttpApp
import org.http4s.Response
import org.http4s.Request


object Main extends  IOApp{

  val config = CORSConfig.default
                          .withAnyOrigin(false)
                          .withAllowCredentials(false)
                          .withAllowedMethods(Some(Set(Method.POST,Method.PUT,Method.GET,Method.DELETE)))
                          .withAllowedOrigins(Set("http://localhost:3000"))

  val corsApp: Http[IO,IO] = CORS(Routes.app, config)
  val hstsApp: Kleisli[IO,Request[IO],Response[IO]]=HSTS(corsApp)

  
  override def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder.default[IO]
      .withHttpApp(hstsApp)
      .build
      .use(_=>IO.never)
      .as(ExitCode.Success)
}