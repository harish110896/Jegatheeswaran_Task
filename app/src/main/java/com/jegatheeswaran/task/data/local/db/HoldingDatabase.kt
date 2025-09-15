package com.jegatheeswaran.task.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jegatheeswaran.task.data.local.dao.HoldingDao
import com.jegatheeswaran.task.data.model.HoldingEntity

@Database(version = 1, entities = [HoldingEntity::class], exportSchema = false)
abstract class HoldingDatabase : RoomDatabase() {
    abstract fun getHoldingDatabase(): HoldingDao
}