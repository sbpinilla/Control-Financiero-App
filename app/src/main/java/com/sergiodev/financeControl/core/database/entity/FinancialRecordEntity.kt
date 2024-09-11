package com.sergiodev.financeControl.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sergiodev.financeControl.core.enums.TransactionType
import java.util.Date

@Entity
data class FinancialRecordEntity(
    @PrimaryKey val id: Long,
    val description: String,
    val nature: String,
    val amount: Double,
    val date: Date
)