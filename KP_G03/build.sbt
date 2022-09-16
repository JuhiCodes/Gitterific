name := """Gitterific"""
organization := "concordia"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.6"

libraryDependencies += guice
// Compile the project before generating Eclipse files, so
// that generated .scala or .class files for views and routes are present

EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)


PlayKeys.fileWatchService := play.dev.filewatch.FileWatchService.jdk7(play.sbt.run.toLoggerProxy(sLog.value))
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.2"
libraryDependencies += "org.json" % "json" % "20210307"
libraryDependencies ++= Seq(
  caffeine
)
libraryDependencies += "org.mockito" % "mockito-core" % "1.10.19" % Test


libraryDependencies +=  "org.scalatest" %% "scalatest" % "3.1.2" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.14" % Test

