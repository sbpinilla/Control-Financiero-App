package com.sergiodev.financeControl.core.database.utils

import androidx.room.TypeConverter
import java.util.Date

class ConvertHelper {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}