package model
import io.circe.generic.semiauto.deriveCodec
final case class UserName(userName:String)extends  AnyVal


object  UserName{

  import io.circe.Decoder.decodeString

  //implicit  val  userNameCodec=deriveCodec[UserName]
  implicit val userNameDecoder=decodeString.map(UserName(_))
  
}