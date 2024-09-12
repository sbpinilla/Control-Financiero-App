package com.sergiodev.financeControl.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.sergiodev.financeControl.core.database.entity.FinancialRecordEntity
import com.sergiodev.financeControl.core.database.entity.FinancialRecordKeyEntity

class FinancialRecordKeyRelation {

    @Embedded
    var financialRecordEntity: FinancialRecordEntity? = null

    @Relation(entityColumn = "financialRecordId", parentColumn = "id")
    var financialRecordKeyEntity: List<FinancialRecordKeyEntity>? = null

}