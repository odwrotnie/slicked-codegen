package slicked

import java.sql.Timestamp

import org.joda.time.DateTime

trait SlickMappers {

  self: HasDatabaseProfile =>

  import profile.api._

  implicit lazy val DateTimeMapper = MappedColumnType.base[DateTime, Timestamp](
    (dt: DateTime) => new Timestamp(dt.getMillis),
    (t: Timestamp) => new DateTime(t.getTime)
  )
}
