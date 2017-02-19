package slicked.model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.H2Profile
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
  lazy val schema: profile.SchemaDescription = CompanyTable.schema ++ PersonTable.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table CompanyTable
   *  @param id Database column ID SqlType(INTEGER), PrimaryKey
   *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
   *  @param address Database column ADDRESS SqlType(CLOB) */
  case class Company(id: Int, name: String, address: Option[java.sql.Clob]) extends SlickedRow
  /** GetResult implicit for fetching Company objects using plain SQL queries */

  implicit def GetResultCompany(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Clob]]): GR[Company] = GR{
    prs => import prs._
    Company.tupled((<<[Int], <<[String], <<?[java.sql.Clob]))
  }
                
  /** Table description of table COMPANY. Objects of this class serve as prototypes for rows in queries. */
  class CompanyTable(_tableTag: Tag) extends profile.api.Table[Company](_tableTag, "COMPANY") with SlickedTable {
    def * = (id, name, address) <> (Company.tupled, Company.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), address).shaped.<>({r=>import r._; _1.map(_=> Company.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INTEGER), PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(255,varying=true))
    /** Database column ADDRESS SqlType(CLOB) */
    val address: Rep[Option[java.sql.Clob]] = column[Option[java.sql.Clob]]("ADDRESS")
  }
  /** Collection-like TableQuery object for table CompanyTable */
  lazy val CompanyTable = new TableQuery(tag => new CompanyTable(tag))

  /** Entity class storing rows of table PersonTable
   *  @param id Database column ID SqlType(INTEGER), PrimaryKey
   *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
   *  @param company Database column COMPANY SqlType(INTEGER) */
  case class Person(id: Int, name: Option[String], company: Option[Int]) extends SlickedRow
  /** GetResult implicit for fetching Person objects using plain SQL queries */

  implicit def GetResultPerson(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]]): GR[Person] = GR{
    prs => import prs._
    Person.tupled((<<[Int], <<?[String], <<?[Int]))
  }
                
  /** Table description of table PERSON. Objects of this class serve as prototypes for rows in queries. */
  class PersonTable(_tableTag: Tag) extends profile.api.Table[Person](_tableTag, "PERSON") with SlickedTable {
    def * = (id, name, company) <> (Person.tupled, Person.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, company).shaped.<>({r=>import r._; _1.map(_=> Person.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INTEGER), PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[Option[String]] = column[Option[String]]("NAME", O.Length(255,varying=true))
    /** Database column COMPANY SqlType(INTEGER) */
    val company: Rep[Option[Int]] = column[Option[Int]]("COMPANY")

    /** Foreign key referencing CompanyTable (database name PERSON_COMPANY_ID_FK) */
    lazy val companyTableFk = foreignKey("PERSON_COMPANY_ID_FK", company, CompanyTable)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table PersonTable */
  lazy val PersonTable = new TableQuery(tag => new PersonTable(tag))
}
