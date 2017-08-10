name := "slicked-model"

organization := "it.wext"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.11"

val slickVersion = "3.2.1"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.3"

libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickVersion

libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % slickVersion

libraryDependencies += "joda-time" % "joda-time" % "2.9.9"

libraryDependencies += "org.joda" % "joda-convert" % "1.8.2"

libraryDependencies += "com.h2database" % "h2" % "1.4.196"

libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.7-dmr"

lazy val generateSlickedModel = taskKey[Unit]("Generate Model Code")
fullRunTask(generateSlickedModel, Compile, "slicked.codegen.Generate")

//

lazy val configuration = RootProject(uri("https://github.com/odwrotnie/configuration.git"))
lazy val slickedModel = project.in(file(".")).dependsOn(configuration)
