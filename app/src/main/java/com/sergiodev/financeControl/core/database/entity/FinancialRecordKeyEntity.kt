package com.sergiodev.financeControl.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FinancialRecordKeyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val financialRecordId: Long,
    var key: String
)