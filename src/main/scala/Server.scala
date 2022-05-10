import org.http4s.ember.server.EmberServerBuilder
import cats.effect.IO
import config._
import com.comcast.ip4s.Host
import com.comcast.ip4s.Port
import fs2.Stream
import cats.effect.std.Console
import org.http4s.server.Server

object  Server{


    def stream(appConfig:AppConfig): Stream[IO, Server] =for {
        _ <- Stream.eval(Console[IO].println("Starting server"))
        server <-  Stream.eval(EmberServerBuilder.default[IO]
          .withPort(Port.fromInt(appConfig.serverConfig.port.value ).get)
          .withHost(Host.fromString(appConfig.serverConfig.host.value).get )
          .build
          .use(_ =>IO.never)
        )

    } yield  server



        
}
