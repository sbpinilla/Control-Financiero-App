package com.sergiodev.financeControl.financialRecord.data

import androidx.room.PrimaryKey
import com.sergiodev.financeControl.core.database.entity.FinancialRecordEntity
import com.sergiodev.financeControl.core.database.entity.FinancialRecordKeyEntity
import com.sergiodev.financeControl.core.database.relations.FinancialRecordKeyRelation
import com.sergiodev.financeControl.core.enums.TransactionType
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import java.util.Date


fun FinancialRecordModel.toFinancialRecordEntity(): FinancialRecordEntity {
    return FinancialRecordEntity(
        this.id,
        this.description,
        this.nature.name,
        this.amount,
        this.date
    )
}

fun FinancialRecordModel.toListFinancialRecordKeyEntity(): List<FinancialRecordKeyEntity> {
    return this.keys.map { x -> FinancialRecordKeyEntity(financialRecordId = this.id, key = x) }
}


fun FinancialRecordKeyRelation.toListFinancialRecordModel(): FinancialRecordModel {
    return FinancialRecordModel(
        id = this.financialRecordEntity!!.id,
        description = this.financialRecordEntity!!.description,
        nature = TransactionType.valueOf(this.financialRecordEntity!!.nature),
        amount = this.financialRecordEntity!!.amount,
        keys = this.financialRecordKeyEntity!!.map { x -> x.key },
        date = this.financialRecordEntity!!.date
    )
}




