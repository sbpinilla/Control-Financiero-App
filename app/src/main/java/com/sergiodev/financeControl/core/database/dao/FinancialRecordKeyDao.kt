package com.sergiodev.financeControl.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.sergiodev.financeControl.core.database.entity.FinancialRecordKeyEntity
import com.sergiodev.financeControl.core.database.utils.BaseDAO

@Dao
interface FinancialRecordKeyDao : BaseDAO<FinancialRecordKeyEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFinancialRecordKey(items: List<FinancialRecordKeyEntity>)

}