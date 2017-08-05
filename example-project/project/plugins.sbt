lazy val root = (project in file(".")).dependsOn(slickedModelPlugin)

lazy val slickedModelPlugin = uri("https://github.com/odwrotnie/slicked-model.git#master")
