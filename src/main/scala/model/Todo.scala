package model

final case class Todo(value:String) extends  AnyVal


object  Todo{

  import io.circe.{Decoder, Encoder}
  import io.circe.Decoder.decodeString
  import io.circe.Encoder.encodeString

  //implicit  val todoCodec= deriveCodec[Todo]
  implicit  val todoDecoder: Decoder[Todo] =decodeString.map(Todo(_))
  implicit  val todoEncoder: Encoder[Todo] = encodeString.contramap[Todo](_.value)
}