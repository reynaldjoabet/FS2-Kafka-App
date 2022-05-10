package model
import io.circe.generic.semiauto.deriveCodec
final case class Regret(regret:String)extends  AnyVal


object  Regret{

  import io.circe.Decoder.decodeString

  //implicit  val regretCodec=deriveCodec[Regret]
  implicit  val regretDecoder=decodeString.map(Regret(_))
}