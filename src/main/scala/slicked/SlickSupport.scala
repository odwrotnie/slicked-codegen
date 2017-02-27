package slicked

import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.JdbcProfile
import slicked.codegen.SlickedDatabaseConfig

import scala.concurrent._
import scala.concurrent.duration._
import scala.util.Try

trait SlickSupport
  extends HasSlickProfile
    with LazyLogging{

  import profile.api._

  implicit lazy val futureEC = scala.concurrent.ExecutionContext.Implicits.global

  val DURATION = 60 seconds

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
  implicit def futureToSuperFuture[T](f: Future[T]): SuperFuture[T] = new SuperFuture[T](f)
  class SuperFuture[T](under: Future[T]) {
    def await: T = Await.result({
      val f = under
      f.onFailure {
        case t => logger.error(s"Await error: ${ t.getMessage }")
      }
      f
    }, DURATION)
    def awaitSafe: Option[T] = Try(await).toOption
  }

  // Pimped DBIO
  implicit def dbioToSuperDBIO[T](a: DBIO[T]): SuperDBIO[T] = new SuperDBIO[T](a)
  class SuperDBIO[T](under: DBIO[T]) {
    def future: Future[T] = db.run(under)
    def futureTransactionally: Future[T] = db.run(under.transactionally)
    def await: T = futureToSuperFuture(future).await
    def awaitSafe: Option[T] = futureToSuperFuture(future).awaitSafe
    def awaitTransactionally: T = futureToSuperFuture(futureTransactionally).await
    def awaitTransactionallySafe: Option[T] = futureToSuperFuture(futureTransactionally).awaitSafe
  }

  def page[E](query: Query[_, E, Seq], pageNum: Long, pageSize: Int = 10): Future[Seq[E]] =
    query.drop(pageNum * pageSize).take(pageSize).result.future

  def pages[E](query: Query[_, E, Seq], pageSize: Long = 10): Future[Long] = query.length.result
    .map { length: Int =>
      Math.round(Math.ceil(length.toFloat / pageSize))
    } future

  def streamify[E](query: Query[_, E, Seq], pageSize: Int = 128): Stream[E] =
    Stream.from(0) map { pageNum =>
      page(query, pageNum, pageSize).await
    } takeWhile(_.nonEmpty) flatten
}
