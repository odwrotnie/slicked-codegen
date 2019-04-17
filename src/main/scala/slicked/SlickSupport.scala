package slicked

import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.meta.MTable
import slick.lifted.CanBeQueryCondition

import scala.concurrent._
import scala.concurrent.duration._
import scala.util.Try

trait SlickSupport extends SlickMappers with LazyLogging {

  self: HasDatabaseProfile with HasDatabaseInstance =>

  import profile.api._

  implicit lazy val futureEC =
    scala.concurrent.ExecutionContext.Implicits.global

  val DURATION = 3.minutes

  def mTableFromDB(name: String) = MTable.getTables(name).await.headOption

  // Wait for the result
  def await[R](f: => Future[R]): R = Await.result(f, DURATION)
  def await[R](futures: Seq[Future[R]]): Seq[R] = {
    var incompleteFutures = futures
    do {
      incompleteFutures = incompleteFutures.filterNot(_.isCompleted)
    } while (!incompleteFutures.isEmpty)
    futures.map(f => Await.result(f, DURATION))
  }

  // Pimped Future
  implicit def futureToSuperFuture[T](f: Future[T]): SuperFuture[T] =
    new SuperFuture[T](f)
  class SuperFuture[T](under: Future[T]) {
    def await: T =
      Await.result({
        val f = under
        f.onFailure {
          case t => logger.error(s"Await error: ${t.getMessage}")
        }
        f
      }, DURATION)
    def awaitSafe: Option[T] = Try(await).toOption
  }

  // Pimped DBIO
  implicit def dbioToSuperDBIO[T](a: DBIO[T]): SuperDBIO[T] =
    new SuperDBIO[T](a)
  class SuperDBIO[T](under: DBIO[T]) {
    def future: Future[T] = db.run(under)
    def futureTransactionally: Future[T] = db.run(under.transactionally)
    def await: T = futureToSuperFuture(future).await
    def awaitSafe: Option[T] = futureToSuperFuture(future).awaitSafe
    def awaitTransactionally: T =
      futureToSuperFuture(futureTransactionally).await
    def awaitTransactionallySafe: Option[T] =
      futureToSuperFuture(futureTransactionally).awaitSafe
  }

  def page[E](query: Query[_, E, Seq],
              pageNum: Long,
              pageSize: Int = 10): Future[Seq[E]] =
    query.drop(pageNum * pageSize).take(pageSize).result.future

  def pages[E](query: Query[_, E, Seq], pageSize: Long = 10): Future[Long] =
    query.length.result.map { length: Int =>
      Math.round(Math.ceil(length.toFloat / pageSize))
    } future

  def streamify[E](query: Query[_, E, Seq], pageSize: Int = 128): Stream[E] =
    Stream.from(0) map { pageNum =>
      page(query, pageNum, pageSize).await
    } takeWhile (_.nonEmpty) flatten

  case class MaybeFilter[X, Y](query: Query[X, Y, Seq]) {
    def filter[T, R: CanBeQueryCondition](data: Option[T])(f: T => X => R) = {
      data.map(v => MaybeFilter(query.withFilter(f(v)))).getOrElse(this)
    }
    def filter[T, R: CanBeQueryCondition](list: Iterable[T])(
        f: Iterable[T] => X => R) =
      if (list.nonEmpty) {
        MaybeFilter(query.withFilter(f(list)))
      } else {
        this
      }
    def filter[R: CanBeQueryCondition](condition: Boolean)(f: X => R) =
      if (condition) {
        MaybeFilter(query.withFilter(f))
      } else {
        this
      }
    def filter[R: CanBeQueryCondition](f: X => R) =
      MaybeFilter(query.withFilter(f))
  }
}
