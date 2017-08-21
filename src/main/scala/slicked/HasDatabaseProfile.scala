package slicked

import slick.jdbc.JdbcProfile

trait HasDatabaseProfile {

  val profile: JdbcProfile
}

trait HasDatabaseInstance {

  self: HasDatabaseProfile =>

  def db: profile.api.Database
}
