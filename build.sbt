name := "Slicked Codegen"

version := "1.0"

scalaVersion := "2.11.8"

val slickVersion = "3.2.0-M2"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0"

libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickVersion

libraryDependencies += "joda-time" % "joda-time" % "2.9.7"

libraryDependencies += "org.joda" % "joda-convert" % "1.8.1"
