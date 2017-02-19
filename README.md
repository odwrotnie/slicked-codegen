# Slicked

## Model Helpers

...

## Autogenerate

`resources/application.conf`

### H2

```
model {
  profile = "slick.jdbc.H2Profile"
  generator = {
    object = "Tables"
    package = "slicked.model"
  }
  db = {
    dataSourceClass = "slick.jdbc.DatabaseUrlDataSource"
    properties = {
      driver = "org.h2.Driver"
      url = "jdbc:h2:./example"
    }
    keepAliveConnection = true
    numThreads = 1
  }
}
```

### MySQL

```
model {
  profile = "slick.jdbc.MySQLProfile"
  generator = {
    object = "Tables"
    package = "slicked.model"
  }
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
