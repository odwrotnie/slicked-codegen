package model

import model.generated.Tables
import slicked.SlickedDatabaseConfig
import slicked.helpers.EntityWithIdHelper

object CompanyHelper
  extends EntityWithIdHelper
    with SlickedDatabaseConfig
    with Tables {

  override def withId(e: CompanyHelper.ENT)(id: Long) = e.copy(id = id)

  override type ENT = Company
  override type TBL = CompanyTable
  override val table = CompanyTable
}
