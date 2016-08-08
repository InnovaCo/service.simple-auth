package eu.inn.auth

import eu.inn.authentication.AuthUser
import eu.inn.hyperbus.model._
import eu.inn.hyperbus.model.annotations.{body, request}

@body("application/vnd.auth+json")
case class AuthenticationRequestBody(credentials: String) extends Body

@body("application/vnd.auth-user+json")
case class AuthenticationResponseBody(authUser: AuthUser) extends Body

@request(Method.GET, "/auth")
case class AuthenticationRequest(body: AuthenticationRequestBody)
  extends Request[Body]
    with DefinedResponse[Ok[AuthenticationResponseBody]]
