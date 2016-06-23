organization := "eu.inn"

name := "simple-auth-service"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "eu.inn"          %% "binders-core"                 % "0.12.85",
  "eu.inn"          %% "auth-service-model"           % "0.1.4",
  "eu.inn"          %% "hyperbus"                     % "0.1.76",
  "eu.inn"          %% "hyperbus-model"               % "0.1.76",
  "eu.inn"          %% "hyperbus-transport"           % "0.1.76",
  "eu.inn"          %% "hyperbus-t-distributed-akka"  % "0.1.76",
  "eu.inn"          %% "hyperbus-t-kafka"             % "0.1.76",
  "eu.inn"          %% "service-config"               % "0.1.6",
  "ch.qos.logback"  % "logback-classic"               % "1.1.2",
  "ch.qos.logback"  % "logback-core"                  % "1.1.2",
  "org.scaldi"      %% "scaldi"                       % "0.5.7"
)

resolvers ++= Seq(
  "Innova libs repo" at "http://repproxy.srv.inn.ru/artifactory/libs-release-local",
  "Innova ext repo" at "http://repproxy.srv.inn.ru/artifactory/ext-release-local",
  Resolver.sonatypeRepo("public")
)

publishTo := Some("Innova libs repo" at "http://repproxy.srv.inn.ru/artifactory/libs-release-local")

credentials += Credentials(Path.userHome / ".ivy2" / ".innova_credentials")
