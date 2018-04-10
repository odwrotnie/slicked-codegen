name := "slicked-model"

organization := "it.wext"

version := "1.0"

scalaVersion := "2.12.4"

val slickVersion = "3.2.1"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5"

libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickVersion

libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % slickVersion

libraryDependencies += "joda-time" % "joda-time" % "2.9.9"

libraryDependencies += "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0"

libraryDependencies += "org.joda" % "joda-convert" % "2.0"

libraryDependencies += "com.h2database" % "h2" % "1.3.176"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.43"

libraryDependencies += "org.hsqldb" % "hsqldb" % "2.4.0"

libraryDependencies += "org.apache.derby" % "derby" % "10.14.1.0"

lazy val generateSlickedModel = taskKey[Unit]("Generate Model Code")
fullRunTask(generateSlickedModel, Compile, "slicked.codegen.Generate")

//

lazy val slickedModel = project.in(file("."))
