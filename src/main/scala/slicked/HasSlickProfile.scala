package slicked

import slick.jdbc.JdbcProfile

trait HasSlickProfile {

  val profile: JdbcProfile
  val db: profile.api.Database
}
