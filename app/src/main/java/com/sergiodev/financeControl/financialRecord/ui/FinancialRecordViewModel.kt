package com.sergiodev.financeControl.financialRecord.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergiodev.financeControl.financialRecord.domain.AddFinancialRecordUseCase
import com.sergiodev.financeControl.financialRecord.domain.GetFinancialRecordFlowUsesCase
import com.sergiodev.financeControl.financialRecord.domain.GetFinancialRecordUsesCase
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancialRecordViewModel @Inject constructor(
    private val addFinancialRecordUseCase: AddFinancialRecordUseCase,
    private val getFinancialRecordUsesCase: GetFinancialRecordUsesCase,
    private val getFinancialRecordFlowUsesCase: GetFinancialRecordFlowUsesCase
) : ViewModel() {

    private val _financialRecords = mutableStateListOf<FinancialRecordModel>()
    val financialRecords: List<FinancialRecordModel> = _financialRecords


    val uiState: StateFlow<FinancialRecordUIState> = getFinancialRecordFlowUsesCase()
        .map { FinancialRecordUIState.Success(it) }
        .catch { FinancialRecordUIState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FinancialRecordUIState.Loading)


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