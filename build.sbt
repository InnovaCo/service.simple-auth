import sbt.Keys._

organization := "eu.inn"

name := "simple-auth-service"

projectMajorVersion := "0.1"

projectBuildNumber := "SNAPSHOT"

version := projectMajorVersion.value + "." + projectBuildNumber.value

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8")

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-feature",
  "-deprecation",
  "-unchecked",
  "-target:jvm-1.8",
  "-encoding", "UTF-8"
)

javacOptions ++= Seq(
  "-source", "1.8",
  "-target", "1.8",
  "-encoding", "UTF-8",
  "-Xlint:unchecked",
  "-Xlint:deprecation"
)

libraryDependencies ++= Seq(
  "eu.inn"          %% "binders-core"                 % "0.12.85",
  "eu.inn"          %% "auth-service-model"           % "0.1.6",
  "eu.inn"          %% "hyperbus"                     % "0.1.76",
  "eu.inn"          %% "hyperbus-model"               % "0.1.76",
  "eu.inn"          %% "hyperbus-transport"           % "0.1.76",
  "eu.inn"          %% "hyperbus-t-distributed-akka"  % "0.1.76",
  "eu.inn"          %% "hyperbus-t-kafka"             % "0.1.76",
  "eu.inn"          %% "service-config"               % "0.1.6",
  "ch.qos.logback"  % "logback-classic"               % "1.1.2",
  "ch.qos.logback"  % "logback-core"                  % "1.1.2",
  "io.spray"        %% "spray-can"                    % "1.3.3",
  "org.scaldi"      %% "scaldi"                       % "0.5.7"
)

resolvers ++= Seq(
  "Innova libs repo" at "http://repproxy.srv.inn.ru/artifactory/libs-release-local",
  "Innova ext repo" at "http://repproxy.srv.inn.ru/artifactory/ext-release-local",
  Resolver.sonatypeRepo("public")
)

lazy val root = (project in file(".")). enablePlugins(BuildInfoPlugin)

val projectMajorVersion = settingKey[String]("Defines the major version number")

val projectBuildNumber = settingKey[String]("Defines the build number")

buildInfoPackage := "eu.inn.facade"

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
