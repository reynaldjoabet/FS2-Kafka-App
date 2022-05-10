package routes
import model._
import org.http4s.dsl.Http4sDsl
import cats.effect.IO
object EventsRoutes  extends  Http4sDsl[IO]{

  import org.http4s.server.Router
  import org.http4s.{HttpApp, HttpRoutes}

  private  val prefix="/api/v1"
  private val routes= HttpRoutes.of[IO] {
    case GET -> Root / "health" => Ok("Doing just fine dude")

    case req@POST -> Root/"event"=>
      req.attemptAs[Event]
        .foldF(
          _ =>BadRequest("An error occurred ,check the request body"),
           event => IO.println(event).*> (Created("Created"))
        )
  }

  val router: HttpRoutes[IO] = Router(prefix->routes)
}
