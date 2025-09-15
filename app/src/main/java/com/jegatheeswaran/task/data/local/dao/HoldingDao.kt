package com.jegatheeswaran.task.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jegatheeswaran.task.data.model.HoldingEntity

@Dao
interface HoldingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(holdings: List<HoldingEntity>)

    @Query("SELECT * FROM holding")
    suspend fun getAllHoldingListing(): List<HoldingEntity>
}