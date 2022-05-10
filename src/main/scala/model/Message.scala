package model
import io.circe.generic.semiauto.deriveCodec
final case class Message(message: String)extends  AnyVal


object  Message{

  import io.circe.Decoder.decodeString

  //implicit  val messageCodec= deriveCodec[Message]
  implicit  val messageDecoder=decodeString.map(Message(_))
}