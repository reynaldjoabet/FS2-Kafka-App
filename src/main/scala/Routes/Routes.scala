package Routes

import cats.effect.IO
import org.http4s.dsl.Http4sDsl
import org.http4s.{HttpApp, HttpRoutes}

object Routes extends Http4sDsl[IO] {
  def app: HttpApp[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "hello" => Ok("Hello dude")//.map(_.addCookie())
  }.orNotFound
}