package model

import io.circe.generic.semiauto.deriveCodec
import io.circe.Decoder.decodeString

final case class Mood(value: String) extends AnyVal

object Mood {

  import io.circe.{Decoder, Encoder}
  import io.circe.Encoder.encodeString

  // implicit  val moodCodec= deriveCodec[Mood]
  implicit val moodCodec: Decoder[Mood] = decodeString.map(Mood(_))

  implicit val moodEncoder: Encoder[Mood] =
    encodeString.contramap[Mood](_.value)

}
