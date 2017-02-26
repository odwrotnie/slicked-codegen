package slicked

import java.sql.Timestamp

import org.joda.time.DateTime
import slicked.codegen.SlickedDBConfig

object SlickMappers
  extends SlickedDBConfig {

  import dbConfig.profile.api._

  implicit val DateTimeMapper = MappedColumnType.base[DateTime, Timestamp](
    (dt: DateTime) => new Timestamp(dt.getMillis),
    (t: Timestamp) => new DateTime(t.getTime)
  )
}
