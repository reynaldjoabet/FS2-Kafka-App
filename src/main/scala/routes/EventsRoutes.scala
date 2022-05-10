package routes

import org.http4s.dsl.Http4sDsl
import cats.effect.IO
case object EventsRoutes  extends  Http4sDsl[IO]{

  import org.http4s.server.Router
  import org.http4s.{HttpApp, HttpRoutes}

  private  val prefix="/api/v1"
 private val routes= HttpRoutes.of[IO] {
    case GET -> Root / "hello" => Ok("Hello dude")//.map(_.addCookie())
  }
  
  val router: HttpRoutes[IO] = Router(prefix->routes)
}
