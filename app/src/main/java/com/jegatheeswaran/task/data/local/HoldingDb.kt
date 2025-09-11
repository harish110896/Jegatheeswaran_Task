package com.jegatheeswaran.task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HoldingEntity::class], version = 1, exportSchema = false)
abstract class HoldingDb : RoomDatabase() {
    abstract fun dao(): HoldingDao
}