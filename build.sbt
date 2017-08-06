name := "slicked-model"

organization := "it.wext"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

val slickVersion = "3.2.0"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.7"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0"

libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickVersion

libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % slickVersion

libraryDependencies += "joda-time" % "joda-time" % "2.9.7"

libraryDependencies += "org.joda" % "joda-convert" % "1.8.1"

libraryDependencies += "com.h2database" % "h2" % "1.4.187"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.34"

lazy val generateSlickedModel = taskKey[Unit]("Generate Model Code")
fullRunTask(generateSlickedModel, Compile, "slicked.codegen.Generate")

//

lazy val configuration = RootProject(uri("https://github.com/odwrotnie/configuration.git"))
lazy val slickedModel = project.in(file(".")).dependsOn(configuration)
