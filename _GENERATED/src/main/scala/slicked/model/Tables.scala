package slicked.model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = class slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  
  // Custom imports start
  import rzepaw.slicked.SlickedRow
  import rzepaw.slicked.SlickedTable
  import rzepaw.slicked.SlickMappers._
  // Custom imports end

  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = EvNewDataTable.schema ++ ValueofTable.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table EvNewDataTable
   *  @param timestamp Database column TIMESTAMP SqlType(TIMESTAMP)
   *  @param tpe Database column TPE SqlType(VARCHAR), Length(16,true)
   *  @param data Database column DATA SqlType(TEXT)
   *  @param source Database column SOURCE SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param id Database column ID SqlType(INT), AutoInc, PrimaryKey
   *  @param foreignId Database column FOREIGN_ID SqlType(VARCHAR), Length(255,true), Default(None) */
  case class EvNewData(timestamp: org.joda.time.DateTime, tpe: String, data: String, source: Option[String] = None, id: Int, foreignId: Option[String] = None) extends SlickedRow
  /** GetResult implicit for fetching EvNewData objects using plain SQL queries */

  implicit def GetResultEvNewData(implicit e0: GR[org.joda.time.DateTime], e1: GR[String], e2: GR[Option[String]], e3: GR[Int]): GR[EvNewData] = GR{
    prs => import prs._
    EvNewData.tupled((<<[org.joda.time.DateTime], <<[String], <<[String], <<?[String], <<[Int], <<?[String]))
  }
                
  /** Table description of table EV_NEW_DATA. Objects of this class serve as prototypes for rows in queries. */
  class EvNewDataTable(_tableTag: Tag) extends profile.api.Table[EvNewData](_tableTag, Some("forex"), "EV_NEW_DATA") with SlickedTable {
    def * = (timestamp, tpe, data, source, id, foreignId) <> (EvNewData.tupled, EvNewData.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(timestamp), Rep.Some(tpe), Rep.Some(data), source, Rep.Some(id), foreignId).shaped.<>({r=>import r._; _1.map(_=> EvNewData.tupled((_1.get, _2.get, _3.get, _4, _5.get, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column TIMESTAMP SqlType(TIMESTAMP) */
    val timestamp: Rep[org.joda.time.DateTime] = column[org.joda.time.DateTime]("TIMESTAMP")
    /** Database column TPE SqlType(VARCHAR), Length(16,true) */
    val tpe: Rep[String] = column[String]("TPE", O.Length(16,varying=true))
    /** Database column DATA SqlType(TEXT) */
    val data: Rep[String] = column[String]("DATA")
    /** Database column SOURCE SqlType(VARCHAR), Length(255,true), Default(None) */
    val source: Rep[Option[String]] = column[Option[String]]("SOURCE", O.Length(255,varying=true), O.Default(None))
    /** Database column ID SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column FOREIGN_ID SqlType(VARCHAR), Length(255,true), Default(None) */
    val foreignId: Rep[Option[String]] = column[Option[String]]("FOREIGN_ID", O.Length(255,varying=true), O.Default(None))

    /** Index over (foreignId) (database name EV_NEW_DATA_FOREIGN_ID_index) */
    val index1 = index("EV_NEW_DATA_FOREIGN_ID_index", foreignId)
  }
  /** Collection-like TableQuery object for table EvNewDataTable */
  lazy val EvNewDataTable = new TableQuery(tag => new EvNewDataTable(tag))

  /** Entity class storing rows of table ValueofTable
   *  @param of Database column OF SqlType(VARCHAR), Length(16,true)
   *  @param in Database column IN SqlType(VARCHAR), Length(16,true)
   *  @param timestamp Database column TIMESTAMP SqlType(TIMESTAMP)
   *  @param `val` Database column VAL SqlType(INT)
   *  @param info Database column INFO SqlType(TEXT), Default(None) */
  case class Valueof(of: String, in: String, timestamp: org.joda.time.DateTime, `val`: Int, info: Option[String] = None) extends SlickedRow
  /** GetResult implicit for fetching Valueof objects using plain SQL queries */

  implicit def GetResultValueof(implicit e0: GR[String], e1: GR[org.joda.time.DateTime], e2: GR[Int], e3: GR[Option[String]]): GR[Valueof] = GR{
    prs => import prs._
    Valueof.tupled((<<[String], <<[String], <<[org.joda.time.DateTime], <<[Int], <<?[String]))
  }
                
  /** Table description of table VALUEOF. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: val */
  class ValueofTable(_tableTag: Tag) extends profile.api.Table[Valueof](_tableTag, Some("forex"), "VALUEOF") with SlickedTable {
    def * = (of, in, timestamp, `val`, info) <> (Valueof.tupled, Valueof.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(of), Rep.Some(in), Rep.Some(timestamp), Rep.Some(`val`), info).shaped.<>({r=>import r._; _1.map(_=> Valueof.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column OF SqlType(VARCHAR), Length(16,true) */
    val of: Rep[String] = column[String]("OF", O.Length(16,varying=true))
    /** Database column IN SqlType(VARCHAR), Length(16,true) */
    val in: Rep[String] = column[String]("IN", O.Length(16,varying=true))
    /** Database column TIMESTAMP SqlType(TIMESTAMP) */
    val timestamp: Rep[org.joda.time.DateTime] = column[org.joda.time.DateTime]("TIMESTAMP")
    /** Database column VAL SqlType(INT)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `val`: Rep[Int] = column[Int]("VAL")
    /** Database column INFO SqlType(TEXT), Default(None) */
    val info: Rep[Option[String]] = column[Option[String]]("INFO", O.Default(None))

    /** Uniqueness Index over (timestamp,of,in) (database name VALUEOF_TIMESTAMP_OF_IN_pk) */
    val index1 = index("VALUEOF_TIMESTAMP_OF_IN_pk", (timestamp, of, in), unique=true)
  }
  /** Collection-like TableQuery object for table ValueofTable */
  lazy val ValueofTable = new TableQuery(tag => new ValueofTable(tag))
}
