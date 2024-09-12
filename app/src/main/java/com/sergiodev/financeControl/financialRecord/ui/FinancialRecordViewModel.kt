package com.sergiodev.financeControl.financialRecord.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergiodev.financeControl.financialRecord.domain.AddFinancialRecordUseCase
import com.sergiodev.financeControl.financialRecord.domain.GetFinancialRecordUsesCase
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancialRecordViewModel @Inject constructor(
    private val addFinancialRecordUseCase: AddFinancialRecordUseCase,
    private val getFinancialRecordUsesCase: GetFinancialRecordUsesCase

) : ViewModel() {

    private val _financialRecords = mutableStateListOf<FinancialRecordModel>()
    val financialRecords: List<FinancialRecordModel> = _financialRecords

    fun onFinancialRecordAdd(financialRecordModel: FinancialRecordModel) {
        _financialRecords.add(financialRecordModel)

        viewModelScope.launch {
            addFinancialRecordUseCase.invoke(financialRecordModel)
            _financialRecords.clear()
            _financialRecords.addAll(getFinancialRecordUsesCase.invoke())
        }

    }

    fun getFinancialRecord() {
        viewModelScope.launch {
            _financialRecords.addAll(getFinancialRecordUsesCase.invoke())
        }
    }

}