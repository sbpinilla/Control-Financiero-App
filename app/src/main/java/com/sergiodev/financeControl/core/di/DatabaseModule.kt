package com.sergiodev.financeControl.core.di

import android.content.Context
import androidx.room.Room
import com.sergiodev.financeControl.core.database.FinancialRecordDatabase
import com.sergiodev.financeControl.core.database.dao.FinancialRecordDao
import com.sergiodev.financeControl.core.database.dao.FinancialRecordKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideFinancialRecordDao(financialRecordDatabase: FinancialRecordDatabase): FinancialRecordDao {
        return financialRecordDatabase.financialRecordDao()
    }

    @Provides
    fun provideFinancialRecordKeyDao(financialRecordDatabase: FinancialRecordDatabase): FinancialRecordKeyDao {
        return financialRecordDatabase.FinancialRecordKeyDao()
    }

    @Provides
    @Singleton
    fun provideFinancialRecordDataBase(@ApplicationContext appContext: Context): FinancialRecordDatabase {
        return Room.databaseBuilder(appContext, FinancialRecordDatabase::class.java, "FinancialRecordDatabase").build()
    }

}