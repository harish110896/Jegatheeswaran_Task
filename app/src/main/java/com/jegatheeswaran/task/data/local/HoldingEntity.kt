package com.jegatheeswaran.task.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jegatheeswaran.task.data.local.HoldingEntity.Companion.TABLE_NAME
import com.jegatheeswaran.task.data.model.Holding

@Entity(tableName = TABLE_NAME)
data class HoldingEntity(
    @PrimaryKey val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
){
    companion object {
        const val TABLE_NAME = "holding"
    }
}


fun HoldingEntity.toDomain() = Holding(
    symbol, quantity, ltp, avgPrice, close
)