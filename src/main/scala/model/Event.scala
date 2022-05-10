package model
import io.circe.generic.semiauto.deriveDecoder
import  org.http4s.circe.jsonOf

import cats.effect.IO
final case class Event(
                userName:UserName,
                message:Message,
                mood:Mood,
                regret: Option[Regret],
                todo:Todo

                )

object Event{

  implicit  val eventCodec=deriveDecoder[Event]
  implicit  val eventEntityEncoder= jsonOf[IO,Event]
}
