package model

import io.circe.generic.semiauto.deriveCodec
final case class UserName(value: String) extends AnyVal

object UserName {

  import io.circe.{Decoder, Encoder}
  import io.circe.Decoder.decodeString
  import io.circe.Encoder.encodeString

  // implicit  val  userNameCodec=deriveCodec[UserName]
  implicit val userNameDecoder: Decoder[UserName] =
    decodeString.map(UserName(_))

  implicit val userNameEncoder: Encoder[UserName] =
    encodeString.contramap[UserName](_.value)

}
