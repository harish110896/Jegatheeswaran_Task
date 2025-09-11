package com.jegatheeswaran.task.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HoldingDao {
    @Query("SELECT * FROM ${HoldingEntity.TABLE_NAME} ORDER BY symbol")
    suspend fun getAll(): List<HoldingEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHolding(items: List<HoldingEntity>)


    @Query("DELETE FROM ${HoldingEntity.TABLE_NAME}")
    suspend fun clear()

    //write a query for local search
}