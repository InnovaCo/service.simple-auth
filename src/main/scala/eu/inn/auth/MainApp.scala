package eu.inn.auth

import eu.inn.auth.modules.MainModule
import eu.inn.hyperbus.Hyperbus
import scaldi.Injectable

class MainApp extends App with Injectable {

  implicit val injector = new MainModule

  val hyperbus = inject[Hyperbus]

}
