package com.sergiodev.financeControl.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sergiodev.financeControl.core.database.dao.FinancialRecordDao
import com.sergiodev.financeControl.core.database.dao.FinancialRecordKeyDao
import com.sergiodev.financeControl.core.database.entity.FinancialRecordEntity
import com.sergiodev.financeControl.core.database.entity.FinancialRecordKeyEntity
import com.sergiodev.financeControl.core.database.utils.ConvertHelper


@Database(entities = [FinancialRecordEntity::class, FinancialRecordKeyEntity::class], version = 1)
@TypeConverters(ConvertHelper::class)
abstract class FinancialRecordDatabase : RoomDatabase() {

    abstract fun financialRecordDao(): FinancialRecordDao
    abstract fun FinancialRecordKeyDao(): FinancialRecordKeyDao
}