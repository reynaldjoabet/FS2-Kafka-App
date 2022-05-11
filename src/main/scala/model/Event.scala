package model
import io.circe.generic.semiauto.deriveCodec
import org.http4s.circe.jsonOf
import io.circe.{Codec, Decoder}
import org.http4s.EntityDecoder
import cats.effect.IO
final case class Event(
                userName:UserName,
                message:Message,
                mood:Mood,
                regret: Option[Regret],
                todo:Todo

                )

object Event{


  implicit  val eventCodec: Codec.AsObject[Event] =deriveCodec[Event]

  implicit  val eventEntityDecoder= jsonOf[IO,Event]

}
