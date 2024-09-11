package com.sergiodev.financeControl.financialRecord.domain

import com.sergiodev.financeControl.financialRecord.data.FinancialRecordRepository
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import javax.inject.Inject

class AddFinancialRecordUseCase @Inject constructor(private val financialRecordRepository: FinancialRecordRepository) {

    suspend operator fun invoke(financialRecordModel: FinancialRecordModel) {
        financialRecordRepository.addFinancialRecord(financialRecordModel)
    }

}