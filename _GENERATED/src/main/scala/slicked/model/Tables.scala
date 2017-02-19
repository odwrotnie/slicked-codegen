package slicked.model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
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
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(ContestTable.schema, LooongTable.schema, ParticipantTable.schema, PersonTable.schema, PlaceTable.schema, TskTable.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table ContestTable
   *  @param id Database column ID SqlType(INT), AutoInc, PrimaryKey
   *  @param dateTime Database column DATE_TIME SqlType(DATETIME), Default(None)
   *  @param description Database column DESCRIPTION SqlType(TEXT), Default(None)
   *  @param name Database column NAME SqlType(VARCHAR), Length(255,true)
   *  @param winner Database column WINNER SqlType(INT), Default(None)
   *  @param place Database column PLACE SqlType(INT), Default(None) */
  case class Contest(id: Int, dateTime: Option[org.joda.time.DateTime] = None, description: Option[String] = None, name: String, winner: Option[Int] = None, place: Option[Int] = None) extends SlickedRow
  /** GetResult implicit for fetching Contest objects using plain SQL queries */

  implicit def GetResultContest(implicit e0: GR[Int], e1: GR[Option[org.joda.time.DateTime]], e2: GR[Option[String]], e3: GR[String], e4: GR[Option[Int]]): GR[Contest] = GR{
    prs => import prs._
    Contest.tupled((<<[Int], <<?[org.joda.time.DateTime], <<?[String], <<[String], <<?[Int], <<?[Int]))
  }
                
  /** Table description of table CONTEST. Objects of this class serve as prototypes for rows in queries. */
  class ContestTable(_tableTag: Tag) extends profile.api.Table[Contest](_tableTag, Some("slick"), "CONTEST") with SlickedTable {
    def * = (id, dateTime, description, name, winner, place) <> (Contest.tupled, Contest.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), dateTime, description, Rep.Some(name), winner, place).shaped.<>({r=>import r._; _1.map(_=> Contest.tupled((_1.get, _2, _3, _4.get, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column DATE_TIME SqlType(DATETIME), Default(None) */
    val dateTime: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("DATE_TIME", O.Default(None))
    /** Database column DESCRIPTION SqlType(TEXT), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("DESCRIPTION", O.Default(None))
    /** Database column NAME SqlType(VARCHAR), Length(255,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(255,varying=true))
    /** Database column WINNER SqlType(INT), Default(None) */
    val winner: Rep[Option[Int]] = column[Option[Int]]("WINNER", O.Default(None))
    /** Database column PLACE SqlType(INT), Default(None) */
    val place: Rep[Option[Int]] = column[Option[Int]]("PLACE", O.Default(None))

    /** Foreign key referencing PersonTable (database name CONTEST_PERSON_ID_fk) */
    lazy val personTableFk = foreignKey("CONTEST_PERSON_ID_fk", winner, PersonTable)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing PlaceTable (database name CONTEST_PLACE_ID_fk) */
    lazy val placeTableFk = foreignKey("CONTEST_PLACE_ID_fk", place, PlaceTable)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.SetNull)
  }
  /** Collection-like TableQuery object for table ContestTable */
  lazy val ContestTable = new TableQuery(tag => new ContestTable(tag))

  /** Row type of table LooongTable */

  /** Constructor for Looong providing default values if available in the database schema. */
  case class Looong(column1: Option[Int] = None, column2: Option[Int] = None, column3: Option[Int] = None, column4: Option[Int] = None, column5: Option[Int] = None, column6: Option[Int] = None, column7: Option[Int] = None, column8: Option[Int] = None, column9: Option[Int] = None, column10: Option[Int] = None, column11: Option[Int] = None, column12: Option[Int] = None, column13: Option[Int] = None, column14: Option[Int] = None, column15: Option[Int] = None, column16: Option[Int] = None, column17: Option[Int] = None, column18: Option[Int] = None, column19: Option[Int] = None, column20: Option[Int] = None, column21: Option[Int] = None, column22: Option[Int] = None, column23: Option[Int] = None) extends SlickedRow
  type LooongList = HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HCons[Option[Int],HNil]]]]]]]]]]]]]]]]]]]]]]]
  object Looong {
    def apply(hList: LooongList): Looong = hList match {
      case column1 :: column2 :: column3 :: column4 :: column5 :: column6 :: column7 :: column8 :: column9 :: column10 :: column11 :: column12 :: column13 :: column14 :: column15 :: column16 :: column17 :: column18 :: column19 :: column20 :: column21 :: column22 :: column23 :: HNil => new Looong(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12, column13, column14, column15, column16, column17, column18, column19, column20, column21, column22, column23)
      case _ => throw new Exception("malformed HList")
    }
    def unapply(row: Looong) = Some(row.column1 :: row.column2 :: row.column3 :: row.column4 :: row.column5 :: row.column6 :: row.column7 :: row.column8 :: row.column9 :: row.column10 :: row.column11 :: row.column12 :: row.column13 :: row.column14 :: row.column15 :: row.column16 :: row.column17 :: row.column18 :: row.column19 :: row.column20 :: row.column21 :: row.column22 :: row.column23 :: HNil)
  }
                  
  /** GetResult implicit for fetching Looong objects using plain SQL queries */

  implicit def GetResultLooong(implicit e0: GR[Option[Int]]): GR[Looong] = GR{
    prs => import prs._
    Looong.apply(<<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: <<?[Int] :: HNil)
  }
                
  /** Table description of table LOOONG. Objects of this class serve as prototypes for rows in queries. */
  class LooongTable(_tableTag: Tag) extends profile.api.Table[Looong](_tableTag, Some("slick"), "LOOONG") with SlickedTable {
    def * = column1 :: column2 :: column3 :: column4 :: column5 :: column6 :: column7 :: column8 :: column9 :: column10 :: column11 :: column12 :: column13 :: column14 :: column15 :: column16 :: column17 :: column18 :: column19 :: column20 :: column21 :: column22 :: column23 :: HNil <> (Looong.apply, Looong.unapply)

    /** Database column column_1 SqlType(INT), Default(None) */
    val column1: Rep[Option[Int]] = column[Option[Int]]("column_1", O.Default(None))
    /** Database column column_2 SqlType(INT), Default(None) */
    val column2: Rep[Option[Int]] = column[Option[Int]]("column_2", O.Default(None))
    /** Database column column_3 SqlType(INT), Default(None) */
    val column3: Rep[Option[Int]] = column[Option[Int]]("column_3", O.Default(None))
    /** Database column column_4 SqlType(INT), Default(None) */
    val column4: Rep[Option[Int]] = column[Option[Int]]("column_4", O.Default(None))
    /** Database column column_5 SqlType(INT), Default(None) */
    val column5: Rep[Option[Int]] = column[Option[Int]]("column_5", O.Default(None))
    /** Database column column_6 SqlType(INT), Default(None) */
    val column6: Rep[Option[Int]] = column[Option[Int]]("column_6", O.Default(None))
    /** Database column column_7 SqlType(INT), Default(None) */
    val column7: Rep[Option[Int]] = column[Option[Int]]("column_7", O.Default(None))
    /** Database column column_8 SqlType(INT), Default(None) */
    val column8: Rep[Option[Int]] = column[Option[Int]]("column_8", O.Default(None))
    /** Database column column_9 SqlType(INT), Default(None) */
    val column9: Rep[Option[Int]] = column[Option[Int]]("column_9", O.Default(None))
    /** Database column column_10 SqlType(INT), Default(None) */
    val column10: Rep[Option[Int]] = column[Option[Int]]("column_10", O.Default(None))
    /** Database column column_11 SqlType(INT), Default(None) */
    val column11: Rep[Option[Int]] = column[Option[Int]]("column_11", O.Default(None))
    /** Database column column_12 SqlType(INT), Default(None) */
    val column12: Rep[Option[Int]] = column[Option[Int]]("column_12", O.Default(None))
    /** Database column column_13 SqlType(INT), Default(None) */
    val column13: Rep[Option[Int]] = column[Option[Int]]("column_13", O.Default(None))
    /** Database column column_14 SqlType(INT), Default(None) */
    val column14: Rep[Option[Int]] = column[Option[Int]]("column_14", O.Default(None))
    /** Database column column_15 SqlType(INT), Default(None) */
    val column15: Rep[Option[Int]] = column[Option[Int]]("column_15", O.Default(None))
    /** Database column column_16 SqlType(INT), Default(None) */
    val column16: Rep[Option[Int]] = column[Option[Int]]("column_16", O.Default(None))
    /** Database column column_17 SqlType(INT), Default(None) */
    val column17: Rep[Option[Int]] = column[Option[Int]]("column_17", O.Default(None))
    /** Database column column_18 SqlType(INT), Default(None) */
    val column18: Rep[Option[Int]] = column[Option[Int]]("column_18", O.Default(None))
    /** Database column column_19 SqlType(INT), Default(None) */
    val column19: Rep[Option[Int]] = column[Option[Int]]("column_19", O.Default(None))
    /** Database column column_20 SqlType(INT), Default(None) */
    val column20: Rep[Option[Int]] = column[Option[Int]]("column_20", O.Default(None))
    /** Database column column_21 SqlType(INT), Default(None) */
    val column21: Rep[Option[Int]] = column[Option[Int]]("column_21", O.Default(None))
    /** Database column column_22 SqlType(INT), Default(None) */
    val column22: Rep[Option[Int]] = column[Option[Int]]("column_22", O.Default(None))
    /** Database column column_23 SqlType(INT), Default(None) */
    val column23: Rep[Option[Int]] = column[Option[Int]]("column_23", O.Default(None))
  }
  /** Collection-like TableQuery object for table LooongTable */
  lazy val LooongTable = new TableQuery(tag => new LooongTable(tag))

  /** Entity class storing rows of table ParticipantTable
   *  @param personId Database column PERSON_ID SqlType(INT)
   *  @param contestId Database column CONTEST_ID SqlType(INT)
   *  @param timestamp Database column TIMESTAMP SqlType(TIMESTAMP) */
  case class Participant(personId: Int, contestId: Int, timestamp: Option[org.joda.time.DateTime]) extends SlickedRow
  /** GetResult implicit for fetching Participant objects using plain SQL queries */

  implicit def GetResultParticipant(implicit e0: GR[Int], e1: GR[Option[org.joda.time.DateTime]]): GR[Participant] = GR{
    prs => import prs._
    Participant.tupled((<<[Int], <<[Int], <<?[org.joda.time.DateTime]))
  }
                
  /** Table description of table PARTICIPANT. Objects of this class serve as prototypes for rows in queries. */
  class ParticipantTable(_tableTag: Tag) extends profile.api.Table[Participant](_tableTag, Some("slick"), "PARTICIPANT") with SlickedTable {
    def * = (personId, contestId, timestamp) <> (Participant.tupled, Participant.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(personId), Rep.Some(contestId), timestamp).shaped.<>({r=>import r._; _1.map(_=> Participant.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column PERSON_ID SqlType(INT) */
    val personId: Rep[Int] = column[Int]("PERSON_ID")
    /** Database column CONTEST_ID SqlType(INT) */
    val contestId: Rep[Int] = column[Int]("CONTEST_ID")
    /** Database column TIMESTAMP SqlType(TIMESTAMP) */
    val timestamp: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("TIMESTAMP")

    /** Foreign key referencing ContestTable (database name PARTICIPANT_ibfk_2) */
    lazy val contestTableFk = foreignKey("PARTICIPANT_ibfk_2", contestId, ContestTable)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing PersonTable (database name PARTICIPANT_ibfk_1) */
    lazy val personTableFk = foreignKey("PARTICIPANT_ibfk_1", personId, PersonTable)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table ParticipantTable */
  lazy val ParticipantTable = new TableQuery(tag => new ParticipantTable(tag))

  /** Entity class storing rows of table PersonTable
   *  @param id Database column ID SqlType(INT), AutoInc, PrimaryKey
   *  @param email Database column EMAIL SqlType(VARCHAR), Length(255,true)
   *  @param firstName Database column FIRST_NAME SqlType(VARCHAR), Length(255,true)
   *  @param lastName Database column LAST_NAME SqlType(VARCHAR), Length(255,true)
   *  @param password Database column PASSWORD SqlType(BLOB), Default(None)
   *  @param role Database column ROLE SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param position Database column POSITION SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param phone Database column PHONE SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param compny Database column COMPNY SqlType(BIGINT), Default(None)
   *  @param authToken Database column AUTH_TOKEN SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param dateOfBirth Database column DATE_OF_BIRTH SqlType(DATE), Default(None)
   *  @param nationalId Database column NATIONAL_ID SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param foreignId Database column FOREIGN_ID SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param parent Database column PARENT SqlType(INT), Default(None)
   *  @param lastActive Database column LAST_ACTIVE SqlType(DATETIME), Default(None)
   *  @param male Database column MALE SqlType(BIT) */
  case class Person(id: Int, email: String, firstName: String, lastName: String, password: Option[java.sql.Blob] = None, role: Option[String] = None, position: Option[String] = None, phone: Option[String] = None, compny: Option[Long] = None, authToken: Option[String] = None, dateOfBirth: Option[java.sql.Date] = None, nationalId: Option[String] = None, foreignId: Option[String] = None, parent: Option[Int] = None, lastActive: Option[org.joda.time.DateTime] = None, male: Boolean) extends SlickedRow
  /** GetResult implicit for fetching Person objects using plain SQL queries */

  implicit def GetResultPerson(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[java.sql.Blob]], e3: GR[Option[String]], e4: GR[Option[Long]], e5: GR[Option[java.sql.Date]], e6: GR[Option[Int]], e7: GR[Option[org.joda.time.DateTime]], e8: GR[Boolean]): GR[Person] = GR{
    prs => import prs._
    Person.tupled((<<[Int], <<[String], <<[String], <<[String], <<?[java.sql.Blob], <<?[String], <<?[String], <<?[String], <<?[Long], <<?[String], <<?[java.sql.Date], <<?[String], <<?[String], <<?[Int], <<?[org.joda.time.DateTime], <<[Boolean]))
  }
                
  /** Table description of table PERSON. Objects of this class serve as prototypes for rows in queries. */
  class PersonTable(_tableTag: Tag) extends profile.api.Table[Person](_tableTag, Some("slick"), "PERSON") with SlickedTable {
    def * = (id, email, firstName, lastName, password, role, position, phone, compny, authToken, dateOfBirth, nationalId, foreignId, parent, lastActive, male) <> (Person.tupled, Person.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(email), Rep.Some(firstName), Rep.Some(lastName), password, role, position, phone, compny, authToken, dateOfBirth, nationalId, foreignId, parent, lastActive, Rep.Some(male)).shaped.<>({r=>import r._; _1.map(_=> Person.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column EMAIL SqlType(VARCHAR), Length(255,true) */
    val email: Rep[String] = column[String]("EMAIL", O.Length(255,varying=true))
    /** Database column FIRST_NAME SqlType(VARCHAR), Length(255,true) */
    val firstName: Rep[String] = column[String]("FIRST_NAME", O.Length(255,varying=true))
    /** Database column LAST_NAME SqlType(VARCHAR), Length(255,true) */
    val lastName: Rep[String] = column[String]("LAST_NAME", O.Length(255,varying=true))
    /** Database column PASSWORD SqlType(BLOB), Default(None) */
    val password: Rep[Option[java.sql.Blob]] = column[Option[java.sql.Blob]]("PASSWORD", O.Default(None))
    /** Database column ROLE SqlType(VARCHAR), Length(255,true), Default(None) */
    val role: Rep[Option[String]] = column[Option[String]]("ROLE", O.Length(255,varying=true), O.Default(None))
    /** Database column POSITION SqlType(VARCHAR), Length(255,true), Default(None) */
    val position: Rep[Option[String]] = column[Option[String]]("POSITION", O.Length(255,varying=true), O.Default(None))
    /** Database column PHONE SqlType(VARCHAR), Length(255,true), Default(None) */
    val phone: Rep[Option[String]] = column[Option[String]]("PHONE", O.Length(255,varying=true), O.Default(None))
    /** Database column COMPNY SqlType(BIGINT), Default(None) */
    val compny: Rep[Option[Long]] = column[Option[Long]]("COMPNY", O.Default(None))
    /** Database column AUTH_TOKEN SqlType(VARCHAR), Length(255,true), Default(None) */
    val authToken: Rep[Option[String]] = column[Option[String]]("AUTH_TOKEN", O.Length(255,varying=true), O.Default(None))
    /** Database column DATE_OF_BIRTH SqlType(DATE), Default(None) */
    val dateOfBirth: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("DATE_OF_BIRTH", O.Default(None))
    /** Database column NATIONAL_ID SqlType(VARCHAR), Length(255,true), Default(None) */
    val nationalId: Rep[Option[String]] = column[Option[String]]("NATIONAL_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column FOREIGN_ID SqlType(VARCHAR), Length(255,true), Default(None) */
    val foreignId: Rep[Option[String]] = column[Option[String]]("FOREIGN_ID", O.Length(255,varying=true), O.Default(None))
    /** Database column PARENT SqlType(INT), Default(None) */
    val parent: Rep[Option[Int]] = column[Option[Int]]("PARENT", O.Default(None))
    /** Database column LAST_ACTIVE SqlType(DATETIME), Default(None) */
    val lastActive: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("LAST_ACTIVE", O.Default(None))
    /** Database column MALE SqlType(BIT) */
    val male: Rep[Boolean] = column[Boolean]("MALE")
  }
  /** Collection-like TableQuery object for table PersonTable */
  lazy val PersonTable = new TableQuery(tag => new PersonTable(tag))

  /** Entity class storing rows of table PlaceTable
   *  @param id Database column ID SqlType(INT), PrimaryKey, Default(0)
   *  @param name Database column NAME SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param owner Database column OWNER SqlType(INT), Default(None) */
  case class Place(id: Int = 0, name: Option[String] = None, owner: Option[Int] = None) extends SlickedRow
  /** GetResult implicit for fetching Place objects using plain SQL queries */

  implicit def GetResultPlace(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]]): GR[Place] = GR{
    prs => import prs._
    Place.tupled((<<[Int], <<?[String], <<?[Int]))
  }
                
  /** Table description of table PLACE. Objects of this class serve as prototypes for rows in queries. */
  class PlaceTable(_tableTag: Tag) extends profile.api.Table[Place](_tableTag, Some("slick"), "PLACE") with SlickedTable {
    def * = (id, name, owner) <> (Place.tupled, Place.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, owner).shaped.<>({r=>import r._; _1.map(_=> Place.tupled((_1.get, _2, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INT), PrimaryKey, Default(0) */
    val id: Rep[Int] = column[Int]("ID", O.PrimaryKey, O.Default(0))
    /** Database column NAME SqlType(VARCHAR), Length(255,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("NAME", O.Length(255,varying=true), O.Default(None))
    /** Database column OWNER SqlType(INT), Default(None) */
    val owner: Rep[Option[Int]] = column[Option[Int]]("OWNER", O.Default(None))

    /** Foreign key referencing PersonTable (database name PLACE_PERSON_ID_fk) */
    lazy val personTableFk = foreignKey("PLACE_PERSON_ID_fk", owner, PersonTable)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table PlaceTable */
  lazy val PlaceTable = new TableQuery(tag => new PlaceTable(tag))

  /** Entity class storing rows of table TskTable
   *  @param start Database column start SqlType(DATETIME), Default(None)
   *  @param end Database column end SqlType(DATETIME), Default(None)
   *  @param created Database column created SqlType(TIMESTAMP) */
  case class Tsk(start: Option[org.joda.time.DateTime] = None, end: Option[org.joda.time.DateTime] = None, created: org.joda.time.DateTime) extends SlickedRow
  /** GetResult implicit for fetching Tsk objects using plain SQL queries */

  implicit def GetResultTsk(implicit e0: GR[Option[org.joda.time.DateTime]], e1: GR[org.joda.time.DateTime]): GR[Tsk] = GR{
    prs => import prs._
    Tsk.tupled((<<?[org.joda.time.DateTime], <<?[org.joda.time.DateTime], <<[org.joda.time.DateTime]))
  }
                
  /** Table description of table TSK. Objects of this class serve as prototypes for rows in queries. */
  class TskTable(_tableTag: Tag) extends profile.api.Table[Tsk](_tableTag, Some("slick"), "TSK") with SlickedTable {
    def * = (start, end, created) <> (Tsk.tupled, Tsk.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (start, end, Rep.Some(created)).shaped.<>({r=>import r._; _3.map(_=> Tsk.tupled((_1, _2, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column start SqlType(DATETIME), Default(None) */
    val start: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("start", O.Default(None))
    /** Database column end SqlType(DATETIME), Default(None) */
    val end: Rep[Option[org.joda.time.DateTime]] = column[Option[org.joda.time.DateTime]]("end", O.Default(None))
    /** Database column created SqlType(TIMESTAMP) */
    val created: Rep[org.joda.time.DateTime] = column[org.joda.time.DateTime]("created")
  }
  /** Collection-like TableQuery object for table TskTable */
  lazy val TskTable = new TableQuery(tag => new TskTable(tag))
}
