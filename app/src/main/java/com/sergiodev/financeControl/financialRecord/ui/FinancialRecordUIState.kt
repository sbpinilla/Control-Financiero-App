package com.sergiodev.financeControl.financialRecord.ui

import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel


sealed interface FinancialRecordUIState {

    data object Loading : FinancialRecordUIState
    data class Error(val throwable: Throwable) : FinancialRecordUIState
    data class Success(val list: List<FinancialRecordModel>) : FinancialRecordUIState

}