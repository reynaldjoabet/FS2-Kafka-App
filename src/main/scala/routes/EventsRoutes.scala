package routes

import cats.effect.Async
import cats.implicits._

import kafka._
import model.Event
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.dsl.Http4sDsl

final case class EventsRoutes[F[_]: Async](
  kafkaProducerAlgebra: KafkaProducerAlgebra[F]
) extends Http4sDsl[F] {

  import org.http4s.server.Router
  import org.http4s.HttpRoutes

  private val prefix = "/api/v1"

  private val routes = HttpRoutes.of[F] {
    case GET -> Root / "health" => Ok("Doing just fine dude")

    case req @ POST -> Root / "event" =>
      req
        .attemptAs[Event]
        .foldF(
          _ => BadRequest("An error occurred ,check the request body"),
          event => kafkaProducerAlgebra.publish(event) *> (Created("Created"))
        )
  }

  val router: HttpRoutes[F] = Router(prefix -> routes)

}
