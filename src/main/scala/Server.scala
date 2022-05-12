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
import kafka.{KafkaConsumerAlgebra, KafkaProducerAlgebra}
import cats.implicits._
object  Server{

  val config =
        CORSConfig.default
          .withAnyOrigin(false)
          .withAllowCredentials(false)
          .withAllowedMethods(Some(Set(Method.POST, Method.PUT, Method.GET, Method.DELETE)))
          .withAllowedOrigins(Set("http://localhost:3000"))
  

    def stream(appConfig:AppConfig):Stream[IO, Unit] =for {
        _ <- Stream.eval(Console[IO].println("Starting server"))
        kafkaProducerAlgebra<- Stream(KafkaProducerAlgebra.impl[IO](appConfig.kafkaConfig))
        kafkaConsumerAlgebra<- Stream(KafkaConsumerAlgebra.impl[IO](appConfig.kafkaConfig))
        //_<-Stream.eval(kafkaConsumerAlgebra.consume.compile.drain.start)
        _<-  Stream.eval(EmberServerBuilder.default[IO]
          .withHttpApp(CORS(HSTS(EventsRoutes(kafkaProducerAlgebra).router.orNotFound),config))
          .withPort(Port.fromInt(appConfig.serverConfig.port.value ).get)
          .withHost(Host.fromString(appConfig.serverConfig.host.value).get )
          .build
          .useForever
        ).concurrently(kafkaConsumerAlgebra.consume)


    } yield  ()



        
}
