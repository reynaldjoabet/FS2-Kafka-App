package model
import io.circe.generic.semiauto.deriveCodec
final case class Todo(todo:String) extends  AnyVal


object  Todo{

  import io.circe.Decoder.decodeString

  //implicit  val todoCodec= deriveCodec[Todo]
  implicit  val todoDecoder=decodeString.map(Todo(_))
}