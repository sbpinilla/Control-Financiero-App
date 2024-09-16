package com.sergiodev.financeControl.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sergiodev.financeControl.core.database.entity.FinancialRecordEntity
import com.sergiodev.financeControl.core.database.relations.FinancialRecordKeyRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface FinancialRecordDao {

    @Query("Select * from FinancialRecordEntity order by date desc")
    fun getFinancialRecord(): Flow<List<FinancialRecordEntity>>

    @Query("Select * from FinancialRecordEntity order by date desc")
    fun getFinancialRecordRelation(): Flow<List<FinancialRecordKeyRelation>>

    @Query("Select * from FinancialRecordEntity order by date desc")
    suspend fun getTemFinancialRecordRelation(): List<FinancialRecordKeyRelation>

    @Insert
    suspend fun addFinancialRecord(item: FinancialRecordEntity)

}