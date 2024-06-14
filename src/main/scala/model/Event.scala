package model

import cats.effect.IO

import io.circe.generic.semiauto.deriveCodec
import io.circe.Codec
import org.http4s.circe.jsonOf
import org.http4s.EntityDecoder

final case class Event(
  userName: UserName,
  message: Message,
  mood: Mood,
  regret: Option[Regret],
  todo: Todo
)

object Event {

  implicit val eventCodec: Codec.AsObject[Event] = deriveCodec[Event]

  implicit val eventEntityDecoder: EntityDecoder[IO, Event] = jsonOf[IO, Event]

}
