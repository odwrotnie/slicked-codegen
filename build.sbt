name := "slicked-model"

organization := "it.wext"

version := "1.3.5"

scalaVersion := "2.12.8"

val slickVersion = "3.3.0"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickVersion

libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % slickVersion

libraryDependencies += "joda-time" % "joda-time" % "2.10.1"

libraryDependencies += "com.github.tototoshi" %% "slick-joda-mapper" % "2.4.0"

libraryDependencies += "org.joda" % "joda-convert" % "2.2.0"

libraryDependencies += "com.h2database" % "h2" % "1.4.199"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.47" // Don't change!

libraryDependencies += "org.hsqldb" % "hsqldb" % "2.4.1"

libraryDependencies += "org.apache.derby" % "derby" % "10.15.1.3"
