package eu.inn.authentication.modules

import akka.actor.ActorSystem
import com.typesafe.config.Config
import eu.inn.authentication.BasicAuthenticationService
import eu.inn.config.ConfigLoader
import eu.inn.authentication.utils.HyperbusFactory
import eu.inn.hyperbus.Hyperbus
import scaldi.Module

import scala.concurrent.ExecutionContext

class MainModule extends Module {
  bind [Config]                      identifiedBy 'config               toNonLazy ConfigLoader()
  bind [HyperbusFactory]             identifiedBy 'hbFactory            to new HyperbusFactory(inject [Config])
  bind [Hyperbus]                    identifiedBy 'hyperbus             to inject [HyperbusFactory].hyperbus
  bind [ActorSystem]                 identifiedBy 'actorSystem          to ActorSystem("simple-auth-service", inject [Config])
  bind [ExecutionContext]            identifiedBy 'executionContext     to inject[ActorSystem].dispatcher
  bind [BasicAuthenticationService]  identifiedBy 'authService          to injected[BasicAuthenticationService]
}
