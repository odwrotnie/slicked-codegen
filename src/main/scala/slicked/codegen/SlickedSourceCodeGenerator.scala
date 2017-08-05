package slicked.codegen

import slick.codegen.SourceCodeGenerator
import slick.model.Model

class SlickedSourceCodeGenerator(model: Model)
  extends SourceCodeGenerator(model) {

  // add custom import for added data types
  // override def code = "import my.package.Java8DateTypes._" + "\n" + super.code

  val imports = "slicked.SlickedRow" ::
    "slicked.SlickedTable" ::
    "slicked.SlickMappers._" :: Nil

  override def code = imports.map(i => s"import $i").mkString("\n// Custom imports start\n", "\n", "\n// Custom imports end\n\n") + super.code

  // Customize Scala entity name (case class, etc.)
  override def entityName = (dbTableName: String) => dbTableName.toCamelCase

  // Customize Scala table name (table class, table values, ...)
  override def tableName = (dbTableName: String) => dbTableName.toCamelCase + "Table"

  override def Table = new Table(_) {
    table =>

    override def Column = new Column(_) {
      override def rawType: String = model.tpe match {
        case "java.sql.Timestamp" => "org.joda.time.DateTime"
        case _ => super.rawType
      }
    }

    // Use different factory and extractor functions for tables with > 22 columns
    override def factory   = if(columns.size == 1) TableClass.elementType else if(columns.size <= 22) s"${TableClass.elementType}.tupled" else s"${EntityType.name}.apply"
    override def extractor = if(columns.size <= 22) s"${TableClass.elementType}.unapply" else s"${EntityType.name}.unapply"

    override def EntityType = new EntityTypeDef {

      override def parents: Seq[String] = Seq("SlickedRow")

      override def code = {
        val args = columns.map { c =>
          c.default.map( v =>
            s"${c.name}: ${c.exposedType} = $v"
          ).getOrElse(
            s"${c.name}: ${c.exposedType}"
          )
        }
        val callArgs = columns.map(c => s"${c.name}")
        val types = columns.map(c => c.exposedType)

        val prns = (parents.take(1).map(" extends "+_) ++ parents.drop(1).map(" with "+_)).mkString("")
        if (classEnabled) {
          s"""case class $name(${args.mkString(", ")})$prns"""
        } else {
          s"""/** Constructor for $name providing default values if available in the database schema. */
             |case class $name(${args.map(arg => {s"$arg"}).mkString(", ")})$prns
             |type ${name}List = ${compoundType(types)}
             |object $name {
             |  def apply(hList: ${name}List): $name = hList match {
             |    case ${compoundValue(callArgs)} => new $name(${callArgs.mkString(", ")})
             |    case _ => throw new Exception("malformed HList")
             |  }
             |  def unapply(row: $name) = Some(${compoundValue(callArgs.map(a => s"row.$a"))})
             |}
                """.stripMargin
        }
      }
    }

    override def PlainSqlMapper = new PlainSqlMapperDef {
      override def code = {
        val positional = compoundValue(columnsPositional.map(c => if (c.fakeNullable || c.model.nullable) s"<<?[${c.rawType}]" else s"<<[${c.rawType}]"))
        val dependencies = columns.map(_.exposedType).distinct.zipWithIndex.map{ case (t,i) => s"""e$i: GR[$t]"""}.mkString(", ")
        val rearranged = compoundValue(desiredColumnOrder.map(i => if(columns.size > 22) s"r($i)" else tuple(i)))
        def result(args: String) = s"$factory($args)"
        val body =
          if(autoIncLastAsOption && columns.size > 1){
            s"""
               |val r = $positional
               |import r._
               |${result(rearranged)} // putting AutoInc last
                  """.stripMargin
          } else {
            result(positional)
          }
        s"""implicit def $name(implicit $dependencies): GR[${TableClass.elementType}] = GR{
           |  prs => import prs._
           |  ${indent(body)}
           |}
              """.stripMargin
      }
    }

//    override def Table = new TableDef(model: slick.model.Table) {
//
//    }

    override def TableClass = new TableClassDef {



      override def parents: Seq[String] = Seq("SlickedTable")

      override def star = {
        val struct = compoundValue(columns.map(c=>if(c.fakeNullable)s"Rep.Some(${c.name})" else s"${c.name}"))
        val rhs = s"$struct <> ($factory, $extractor)"
        s"def * = $rhs"
      }

      override def code = {
        val prns = parents.map(" with " + _).mkString("")
        val args = Seq("\""+model.name.table+"\"")
        s"""class $name(_tableTag: Tag) extends profile.api.Table[$elementType](_tableTag, ${args.mkString(", ")})$prns {
           |  ${indent(body.map(_.mkString("\n")).mkString("\n\n"))}
           |}
              """.stripMargin
      }
    }

    def tails(n: Int) = {
      List.fill(n)(".tail").mkString("")
    }

    // override column generator to add additional types
    //      override def Column = new Column(_) {
    //        override def rawType = {
    //          typeMapper(model).getOrElse(super.rawType)
    //        }
    //      }
  }
}