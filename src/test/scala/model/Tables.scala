package model

import slicked.{HasDatabaseProfile, SlickMappers, SlickedDatabaseConfig}

object Tables
  extends SlickedDatabaseConfig
    with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables
  extends SlickMappers {

  self: HasDatabaseProfile =>

  import profile.api._

  // Custom imports start
  import slicked.SlickedRow
  import slicked.SlickedTable
  // Custom imports end

  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = CompanyTable.schema ++ EventTable.schema ++ PersonTable.schema ++ SpinningBottleGamesTable.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table CompanyTable
    *  @param id Database column ID SqlType(BIGINT), PrimaryKey
    *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
    *  @param address Database column ADDRESS SqlType(CLOB) */
  case class Company(id: Long, name: String, address: Option[java.sql.Clob]) extends SlickedRow
  /** GetResult implicit for fetching Company objects using plain SQL queries */
  implicit def GetResultCompany(implicit e0: GR[Long], e1: GR[String], e2: GR[Option[java.sql.Clob]]): GR[Company] = GR{
    prs => import prs._
      Company.tupled((<<[Long], <<[String], <<?[java.sql.Clob]))
  }

  /** Table description of table COMPANY. Objects of this class serve as prototypes for rows in queries. */
  class CompanyTable(_tableTag: Tag) extends profile.api.Table[Company](_tableTag, "COMPANY") with SlickedTable {
    def * = (id, name, address) <> (Company.tupled, Company.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), address).shaped.<>({r=>import r._; _1.map(_=> Company.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(255,varying=true))
    /** Database column ADDRESS SqlType(CLOB) */
    val address: Rep[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("ADDRESS")
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

  /** Entity class storing rows of table PersonTable
    *  @param id Database column ID SqlType(BIGINT), PrimaryKey
    *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
    *  @param company Database column COMPANY SqlType(BIGINT) */
  case class Person(id: Long, name: Option[String], company: Option[Long]) extends SlickedRow
  /** GetResult implicit for fetching Person objects using plain SQL queries */
  implicit def GetResultPerson(implicit e0: GR[Long], e1: GR[Option[String]], e2: GR[Option[Long]]): GR[Person] = GR{
    prs => import prs._
      Person.tupled((<<[Long], <<?[String], <<?[Long]))
  }

  /** Table description of table PERSON. Objects of this class serve as prototypes for rows in queries. */
  class PersonTable(_tableTag: Tag) extends profile.api.Table[Person](_tableTag, "PERSON") with SlickedTable {
    def * = (id, name, company) <> (Person.tupled, Person.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, company).shaped.<>({r=>import r._; _1.map(_=> Person.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(BIGINT), PrimaryKey */
    val id: Rep[Long] = column[Long]("ID", O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[Option[String]] = column[Option[String]]("NAME", O.Length(255,varying=true))
    /** Database column COMPANY SqlType(BIGINT) */
    val company: Rep[Option[Long]] = column[Option[Long]]("COMPANY")

    /** Foreign key referencing CompanyTable (database name PERSON_COMPANY_ID_FK) */
    lazy val companyTableFk = foreignKey("PERSON_COMPANY_ID_FK", company, CompanyTable)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
  }

  /** Collection-like TableQuery object for table PersonTable */
  lazy val PersonTable = new TableQuery(tag => new PersonTable(tag))

  /** Entity class storing rows of table SpinningBottleGamesTable
    *  @param created Database column CREATED SqlType(TIMESTAMP)
    *  @param start Database column START SqlType(TIMESTAMP)
    *  @param end Database column END SqlType(TIMESTAMP)
    *  @param betValue Database column BET_VALUE SqlType(BIGINT)
    *  @param isPrivate Database column IS_PRIVATE SqlType(BOOLEAN)
    *  @param id Database column ID SqlType(VARCHAR), AutoInc, PrimaryKey, Length(255,true) */
  case class SpinningBottleGames(created: Option[org.joda.time.DateTime], start: Option[org.joda.time.DateTime], end: Option[org.joda.time.DateTime], betValue: Option[Long], isPrivate: Option[Boolean], id: String) extends SlickedRow
  /** GetResult implicit for fetching SpinningBottleGames objects using plain SQL queries */
  implicit def GetResultSpinningBottleGames(implicit e0: GR[Option[org.joda.time.DateTime]], e1: GR[Option[Long]], e2: GR[Option[Boolean]], e3: GR[String]): GR[SpinningBottleGames] = GR{
    prs => import prs._
      SpinningBottleGames.tupled((<<?[org.joda.time.DateTime], <<?[org.joda.time.DateTime], <<?[org.joda.time.DateTime], <<?[Long], <<?[Boolean], <<[String]))
  }

  /** Table description of table SPINNING_BOTTLE_GAMES. Objects of this class serve as prototypes for rows in queries. */
  class SpinningBottleGamesTable(_tableTag: Tag) extends profile.api.Table[SpinningBottleGames](_tableTag, "SPINNING_BOTTLE_GAMES") with SlickedTable {
    def * = (created, start, end, betValue, isPrivate, id) <> (SpinningBottleGames.tupled, SpinningBottleGames.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (created, start, end, betValue, isPrivate, Rep.Some(id)).shaped.<>({r=>import r._; _6.map(_=> SpinningBottleGames.tupled((_1, _2, _3, _4, _5, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column CREATED SqlType(TIMESTAMP) */
    val created: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("CREATED")
    /** Database column START SqlType(TIMESTAMP) */
    val start: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("START")
    /** Database column END SqlType(TIMESTAMP) */
    val end: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("END")
    /** Database column BET_VALUE SqlType(BIGINT) */
    val betValue: Rep[Option[Long]] = column[Option[Long]]("BET_VALUE")
    /** Database column IS_PRIVATE SqlType(BOOLEAN) */
    val isPrivate: Rep[Option[Boolean]] = column[Option[Boolean]]("IS_PRIVATE")
    /** Database column ID SqlType(VARCHAR), AutoInc, PrimaryKey, Length(255,true) */
    val id: Rep[String] = column[String]("ID", O.AutoInc, O.PrimaryKey, O.Length(255,varying=true))
  }

  /** Collection-like TableQuery object for table SpinningBottleGamesTable */
  lazy val SpinningBottleGamesTable = new TableQuery(tag => new SpinningBottleGamesTable(tag))
}
