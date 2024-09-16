package com.sergiodev.financeControl.financialRecord.data


import com.sergiodev.financeControl.core.database.dao.FinancialRecordDao
import com.sergiodev.financeControl.core.database.dao.FinancialRecordKeyDao
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinancialRecordRepository @Inject constructor(
    private val financialRecordDao: FinancialRecordDao,
    private val financialRecordKeyDao: FinancialRecordKeyDao
) {

    val financialRecordKeyRelation: Flow<List<FinancialRecordModel>> = financialRecordDao.getFinancialRecordRelation().map { items ->
        items.map { item ->
            item.toListFinancialRecordModel()
        }
    }

    suspend fun addFinancialRecord(financialRecordModel: FinancialRecordModel) {
        financialRecordDao.addFinancialRecord(financialRecordModel.toFinancialRecordEntity())
        financialRecordKeyDao.addFinancialRecordKey(financialRecordModel.toListFinancialRecordKeyEntity())
    }

    suspend fun getFinancialRecord(): List<FinancialRecordModel> {
        return financialRecordDao.getTemFinancialRecordRelation().map { x -> x.toListFinancialRecordModel() }
    }


}