package model

import io.circe.Decoder.decodeString
import io.circe.Encoder.encodeString

final case class Message(value: String) extends AnyVal

object Message {

  import io.circe.{Decoder, Encoder}

  // implicit  val messageCodec= deriveCodec[Message]
  implicit val messageDecoder: Decoder[Message] = decodeString.map(Message(_))

  implicit val messageEncoder: Encoder[Message] =
    encodeString.contramap[Message](_.value)

}
