package com.sergiodev.financeControl.core.database.utils

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDAO<T> {
    @Insert
    suspend fun insert(obj: T)

    @Insert
    suspend fun insertAll(obj: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(obj: T)

    @Insert
    suspend fun insert(vararg obj: T)

    @Update
    suspend fun update(obj: T)

    @Delete
    suspend fun delete(obj: T)
}