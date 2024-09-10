package com.sergiodev.financeControl.core.extension

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun String.formatWithThousandsSeparator(): String {

    val number = this.removeThousandsSeparator().toLongOrNull() ?: return this

    val symbols = DecimalFormatSymbols().apply {
        groupingSeparator = '.'
    }

    val formatter = DecimalFormat("#,###", symbols)

    return formatter.format(number)
}

fun String.removeThousandsSeparator(): String {
    return this.replace(".", "")
}