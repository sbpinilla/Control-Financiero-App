package com.sergiodev.financeControl.financialRecord.data

import com.sergiodev.financeControl.core.database.dao.FinancialRecordDao
import com.sergiodev.financeControl.core.database.dao.FinancialRecordKeyDao
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinancialRecordRepository @Inject constructor(
    private val financialRecordDao: FinancialRecordDao,
    private val financialRecordKeyDao: FinancialRecordKeyDao) {


    suspend fun addFinancialRecord(financialRecordModel: FinancialRecordModel) {

        financialRecordDao.addFinancialRecord(financialRecordModel.toFinancialRecordEntity())
        financialRecordKeyDao.addFinancialRecordKey(financialRecordModel.toListFinancialRecordKeyEntity())

    }

}