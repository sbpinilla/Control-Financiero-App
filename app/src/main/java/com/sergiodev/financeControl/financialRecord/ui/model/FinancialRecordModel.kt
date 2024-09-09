package com.sergiodev.financeControl.financialRecord.ui.model

import com.sergiodev.financeControl.core.enums.TransactionType
import java.util.Date


data class FinancialRecordModel(
    val id: Long = System.currentTimeMillis(),
    val description: String,
    val nature: TransactionType,
    val amount: Double,
    val keys: List<String>,
    val date: Date
)
