package com.sergiodev.financeControl.financialRecord.domain

import com.sergiodev.financeControl.financialRecord.data.FinancialRecordRepository
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFinancialRecordFlowUsesCase @Inject constructor(private val financialRecordRepository: FinancialRecordRepository) {

    operator fun invoke(): Flow<List<FinancialRecordModel>> {
        return financialRecordRepository.financialRecordKeyRelation
    }
}