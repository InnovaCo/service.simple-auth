package eu.inn.authentication

import com.typesafe.config.Config
import eu.inn.hyperbus.Hyperbus
import eu.inn.hyperbus.model._
import eu.inn.hyperbus.transport.api.matchers.{RequestMatcher, Specific}
import eu.inn.hyperbus.transport.api.uri.Uri

import scala.concurrent.{ExecutionContext, Future}

class AuthenticationService(hyperbus: Hyperbus, config: Config)(implicit executionContext: ExecutionContext) {

  val requestMatcher = RequestMatcher(Some(Uri("/auth")), Map(Header.METHOD → Specific(Method.GET)))
  hyperbus.onCommand(requestMatcher) { request: DynamicRequest ⇒
      Future {
        authenticate(request.body.content.credentials.asString) match {
          case Some(authUser) ⇒ AuthenticationResponse(AuthenticationResponseBody(authUser))
          case None ⇒ NotFound(ErrorBody("not-found"))
        }
      }
  }

  def authenticate(credentials: String): Option[AuthUser] = {
    None
  }
}
