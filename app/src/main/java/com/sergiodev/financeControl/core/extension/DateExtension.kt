package com.sergiodev.financeControl.core.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun Date.toFormattedString(format: String = "dd MMM yyyy"): String {
    val format = SimpleDateFormat(format, Locale.getDefault())
    return format.format(this)
}
