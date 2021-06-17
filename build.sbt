name := """play-scala-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play"      %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-play-server"  % "0.18.0-M15"
libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-json-play"    % "0.18.0-M15"
libraryDependencies += "dev.zio"                     %% "zio"                % "1.0.9"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
