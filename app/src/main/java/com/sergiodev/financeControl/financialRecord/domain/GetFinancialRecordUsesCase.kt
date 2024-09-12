package com.sergiodev.financeControl.financialRecord.domain

import com.sergiodev.financeControl.financialRecord.data.FinancialRecordRepository
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import javax.inject.Inject


class GetFinancialRecordUsesCase @Inject constructor(private val financialRecordRepository: FinancialRecordRepository) {
    suspend operator fun invoke(): List<FinancialRecordModel> {
        return financialRecordRepository.getFinancialRecord()
    }
}