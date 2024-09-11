package com.sergiodev.financeControl.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sergiodev.financeControl.core.database.entity.FinancialRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FinancialRecordDao {

    @Query("Select * from FinancialRecordEntity")
    fun getFinancialRecord(): Flow<List<FinancialRecordEntity>>

    @Insert
    suspend fun addFinancialRecord(item: FinancialRecordEntity)

}