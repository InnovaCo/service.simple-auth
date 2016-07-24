package eu.inn.authentication

import com.typesafe.config.Config
import eu.inn.binders.value.Null
import eu.inn.hyperbus.Hyperbus
import eu.inn.hyperbus.model._

import scala.collection.JavaConversions._
import scala.concurrent.{ExecutionContext, Future}

class BasicAuthenticationService(hyperbus: Hyperbus, config: Config)
                                (implicit executionContext: ExecutionContext) {

  hyperbus ~> { request: AuthenticationRequest ⇒
    Future {
      authenticate(request.body.credentials) match {
        case Some(authUser) ⇒
          Ok(AuthenticationResponseBody(authUser))

        case None ⇒
          NotFound(ErrorBody("not-found"))
      }
    }
  }

  def authenticate(authString: String): Option[AuthUser] = {
    var authUser: Option[AuthUser] = None
    val credentials = authString.split(":")
    if (credentials.length != 2) {
      throw new IllegalArgumentException("Wrong credentials format. Should be 'user:password'")
    }
    val login = credentials(0)
    val password = credentials(1)
    getBasicAuthConfigs.foreach { authConfig ⇒
      if (authConfig.hasPath(login)) {
        val userConfig = authConfig.getConfig(login)
        if (userConfig.getString("password") == password) {
          val id = userConfig.getString("id")
          val roles = userConfig.getStringList("roles").toSet
          authUser = Some(AuthUser(id, roles, Null))
        }
      }
    }
    authUser
  }

  def getBasicAuthConfigs: Set[Config] = config.getConfigList("auth-service.basic").toSet
}
