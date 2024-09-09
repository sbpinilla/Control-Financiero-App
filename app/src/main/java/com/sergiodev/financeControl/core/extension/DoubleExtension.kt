package com.sergiodev.financeControl.core.extension

import com.sergiodev.financeControl.core.enums.TransactionType
import java.text.NumberFormat
import java.util.Locale


fun Double.toMoneyFormat(type: TransactionType): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale("es", "CO"))
    numberFormat.maximumFractionDigits = 0
    numberFormat.isGroupingUsed = true

    val formattedAmount = numberFormat.format(this)

    return when (type) {
        TransactionType.CREDIT -> "$ -$formattedAmount,00"
        TransactionType.DEBIT -> "$ $formattedAmount,00"
    }
}