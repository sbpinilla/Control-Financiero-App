package com.sergiodev.financeControl.financialRecord.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.sergiodev.financeControl.financialRecord.domain.AddFinancialRecordUseCase
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FinancialRecordViewModel @Inject constructor(private val addFinancialRecordUseCase: AddFinancialRecordUseCase) : ViewModel() {

    private val _financialRecords = mutableStateListOf<FinancialRecordModel>()
    val financialRecords: List<FinancialRecordModel> = _financialRecords

    fun onFinancialRecordAdd(financialRecordModel: FinancialRecordModel) {
        _financialRecords.add(financialRecordModel)
    }

}