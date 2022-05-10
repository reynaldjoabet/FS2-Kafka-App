import cats.data.Kleisli
import org.http4s.ember.server.EmberServerBuilder
import cats.effect.IO
import config._
import com.comcast.ip4s.Host
import com.comcast.ip4s.Port
import fs2.Stream
import cats.effect.std.Console
import org.http4s.{Http, Method, Request, Response}
import org.http4s.server.Server
import org.http4s.server.middleware.{CORS, CORSConfig, HSTS}
import routes.EventsRoutes

object  Server{
    val config =
        CORSConfig.default
          .withAnyOrigin(false)
          .withAllowCredentials(false)
          .withAllowedMethods(Some(Set(Method.POST, Method.PUT, Method.GET, Method.DELETE)))
          .withAllowedOrigins(Set("http://localhost:3000"))

    val corsApp: Http[IO, IO] = CORS(EventsRoutes.router.orNotFound, config)
    val hstsApp: Kleisli[IO, Request[IO], Response[IO]] = HSTS(corsApp)

    def stream(appConfig:AppConfig): Stream[IO, Server] =for {
        _ <- Stream.eval(Console[IO].println("Starting server"))
        server <-  Stream.eval(EmberServerBuilder.default[IO]
          .withHttpApp(hstsApp)
          .withPort(Port.fromInt(appConfig.serverConfig.port.value ).get)
          .withHost(Host.fromString(appConfig.serverConfig.host.value).get )
          .build
          .use(_ =>IO.never)
        )

    } yield  server



        
}
