package slicked

import slick.jdbc.JdbcProfile

trait DatabaseProfile {
  val profile: JdbcProfile
  val db: profile.api.Database
}
