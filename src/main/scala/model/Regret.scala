package model

import io.circe.generic.semiauto.deriveCodec
final case class Regret(value: String) extends AnyVal

object Regret {

  import io.circe.{Decoder, Encoder}
  import io.circe.Decoder.decodeString
  import io.circe.Encoder.encodeString

  // implicit  val regretCodec=deriveCodec[Regret]
  implicit val regretDecoder: Decoder[Regret] = decodeString.map(Regret(_))

  implicit val regretEncoder: Encoder[Regret] =
    encodeString.contramap[Regret](_.value)

}
