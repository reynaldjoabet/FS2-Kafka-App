package model
import io.circe.generic.semiauto.deriveCodec
import io.circe.Decoder.decodeString
final case class Mood(mood:String) extends  AnyVal

object  Mood{
  //implicit  val moodCodec= deriveCodec[Mood]
  implicit  val moodCodec= decodeString.map(Mood(_))
}