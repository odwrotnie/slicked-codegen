package model

import slicked.SlickedDatabaseConfig
import slicked.helpers.{EntityHelper, EntityWithIdHelper}

object CompanyHelper
    extends EntityWithIdHelper
    with SlickedDatabaseConfig
    with Tables {

  override def withId(e: CompanyHelper.ENT)(id: Long) = e.copy(id = id)

  override type ENT = Company
  override type TBL = CompanyTable
  override val table = CompanyTable
}
