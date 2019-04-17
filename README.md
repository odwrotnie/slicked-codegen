# Slicked Model

## Model Helpers

...

## Autogenerate

### SBT Task

Add to build.sbt:

```
lazy val slickedCodegen = RootProject(uri("git://github.com/odwrotnie/slicked-codegen.git"))
lazy val root = Project("root", file(".")) dependsOn(slickedCodegen)

lazy val gen = taskKey[Unit]("Generate Model Code")
fullRunTask(gen, Compile, "slicked.codegen.Generator")

```

Run `sbt gen` to generate the model

## Configuration

`resources/reference.conf`

### H2

```
generator = {
  object = "Tables"
  package = "model"
  tableFilterRegex = ".*"
}

database {
  profile = "slick.jdbc.H2Profile$"
  db = {
    dataSourceClass = "slick.jdbc.DatabaseUrlDataSource"
    properties = {
      driver = "org.h2.Driver"
      url = "jdbc:h2:./db/h2"
    }
    keepAliveConnection = true
    numThreads = 1
  }
}

```

### MySQL

```
generator = {
  object = "Tables"
  package = "model"
  tableFilterRegex = ".*"
}

database {
  profile = "slick.jdbc.MySQLProfile"
  db = {
    dataSourceClass = com.mysql.jdbc.jdbc2.optional.MysqlDataSource
    properties = {
      serverName = "domain.com"
      databaseName = "slick"
      user = "slick"
      password = "slickslick1234"
      cachePrepStmts = true
      prepStmtCacheSize = 250
      prepStmtCacheSqlLimit = 2048
      useServerPrepStmts = true
    }
    keepAliveConnection = true
    numThreads = 1
  }
}
```
