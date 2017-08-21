//package model
//
//import slicked.DatabaseProfile
//import slicked.helpers.HasEntityWithIdHelper
//
//trait HasCompanyHelper
//  extends HasEntityWithIdHelper {
//
//  self: DatabaseProfile with Tables =>
//
//  val companyHelper = new  EntityWithIdHelper[Company, CompanyTable] {
//    override def table = CompanyTable
//    override def withId(e: Company)(id: Long) = e.copy(id = id.toInt)
//  }
//
//  implicit class RichCompany(e: Company)
//    extends RichEntityWithId[Company, CompanyTable](e) {
//    override def helper = companyHelper
//    def onlyCompanyMethod: String = e.name.toUpperCase
//  }
//}
