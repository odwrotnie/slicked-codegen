package model

trait Tables extends slicked.SlickMappers {

  self: slicked.HasDatabaseProfile =>

  val profile: slick.jdbc.JdbcProfile
  import profile.api._

  
  // Custom imports start
  import slicked.SlickedRow
  import slicked.SlickedTable
  // Custom imports end

  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = CompanyCopy30Table.schema ++ CompanyCopy31Table.schema ++ CompanyTable.schema ++ EventTable.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table CompanyCopy30Table
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
   *  @param address Database column ADDRESS SqlType(VARCHAR) */
  case class CompanyCopy30(id: Long, name: String, address: Option[String]) extends SlickedRow
  /** GetResult implicit for fetching CompanyCopy30 objects using plain SQL queries */
  implicit def GetResultCompanyCopy30(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[CompanyCopy30] = GR{
    prs => import prs._
    CompanyCopy30.tupled((<<[Long], <<[String], <<?[String]))
  }
                
  /** Table description of table COMPANY_COPY_3_0. Objects of this class serve as prototypes for rows in queries. */
  class CompanyCopy30Table(_tableTag: Tag) extends profile.api.Table[CompanyCopy30](_tableTag, "COMPANY_COPY_3_0") with SlickedTable {
    def * = (id, name, address) <> (CompanyCopy30.tupled, CompanyCopy30.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), address).shaped.<>({r=>import r._; _1.map(_=> CompanyCopy30.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(255,varying=true))
    /** Database column ADDRESS SqlType(VARCHAR) */
    val address: Rep[Option[String]] = column[Option[String]]("ADDRESS")
  }
                
  /** Collection-like TableQuery object for table CompanyCopy30Table */
  lazy val CompanyCopy30Table = new TableQuery(tag => new CompanyCopy30Table(tag))

  /** Entity class storing rows of table CompanyCopy31Table
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
   *  @param address Database column ADDRESS SqlType(VARCHAR) */
  case class CompanyCopy31(id: Long, name: String, address: Option[String]) extends SlickedRow
  /** GetResult implicit for fetching CompanyCopy31 objects using plain SQL queries */
  implicit def GetResultCompanyCopy31(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[CompanyCopy31] = GR{
    prs => import prs._
    CompanyCopy31.tupled((<<[Long], <<[String], <<?[String]))
  }
                
  /** Table description of table COMPANY_COPY_3_1. Objects of this class serve as prototypes for rows in queries. */
  class CompanyCopy31Table(_tableTag: Tag) extends profile.api.Table[CompanyCopy31](_tableTag, "COMPANY_COPY_3_1") with SlickedTable {
    def * = (id, name, address) <> (CompanyCopy31.tupled, CompanyCopy31.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), address).shaped.<>({r=>import r._; _1.map(_=> CompanyCopy31.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(255,varying=true))
    /** Database column ADDRESS SqlType(VARCHAR) */
    val address: Rep[Option[String]] = column[Option[String]]("ADDRESS")
  }
                
  /** Collection-like TableQuery object for table CompanyCopy31Table */
  lazy val CompanyCopy31Table = new TableQuery(tag => new CompanyCopy31Table(tag))

  /** Entity class storing rows of table CompanyTable
   *  @param id Database column ID SqlType(BIGINT), AutoInc, PrimaryKey
   *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
   *  @param address Database column ADDRESS SqlType(VARCHAR) */
  case class Company(id: Long, name: String, address: Option[String]) extends SlickedRow
  /** GetResult implicit for fetching Company objects using plain SQL queries */
  implicit def GetResultCompany(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[String]]): GR[Company] = GR{
    prs => import prs._
    Company.tupled((<<[Long], <<[String], <<?[String]))
  }
                
  /** Table description of table COMPANY. Objects of this class serve as prototypes for rows in queries. */
  class CompanyTable(_tableTag: Tag) extends profile.api.Table[Company](_tableTag, "COMPANY") with SlickedTable {
    def * = (id, name, address) <> (Company.tupled, Company.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), address).shaped.<>({r=>import r._; _1.map(_=> Company.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(255,varying=true))
    /** Database column ADDRESS SqlType(VARCHAR) */
    val address: Rep[Option[String]] = column[Option[String]]("ADDRESS")
  }
                
  /** Collection-like TableQuery object for table CompanyTable */
  lazy val CompanyTable = new TableQuery(tag => new CompanyTable(tag))

  /** Entity class storing rows of table EventTable
   *  @param timestamp Database column TIMESTAMP SqlType(TIMESTAMP)
   *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
   *  @param desc Database column DESC SqlType(VARCHAR), Length(1024,true) */
  case class Event(timestamp: Option[org.joda.time.DateTime], name: Option[String], desc: Option[String]) extends SlickedRow
  /** GetResult implicit for fetching Event objects using plain SQL queries */
  implicit def GetResultEvent(implicit e0: GR[Option[org.joda.time.DateTime]], e1: GR[Option[String]]): GR[Event] = GR{
    prs => import prs._
    Event.tupled((<<?[org.joda.time.DateTime], <<?[String], <<?[String]))
  }
                
  /** Table description of table EVENT. Objects of this class serve as prototypes for rows in queries. */
  class EventTable(_tableTag: Tag) extends profile.api.Table[Event](_tableTag, "EVENT") with SlickedTable {
    def * = (timestamp, name, desc) <> (Event.tupled, Event.unapply)

    /** Database column TIMESTAMP SqlType(TIMESTAMP) */
    val timestamp: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("TIMESTAMP")
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[Option[String]] = column[Option[String]]("NAME", O.Length(255,varying=true))
    /** Database column DESC SqlType(VARCHAR), Length(1024,true) */
    val desc: Rep[Option[String]] = column[Option[String]]("DESC", O.Length(1024,varying=true))
  }
                
  /** Collection-like TableQuery object for table EventTable */
  lazy val EventTable = new TableQuery(tag => new EventTable(tag))
}
      
