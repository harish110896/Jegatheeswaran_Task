package com.jegatheeswaran.task.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "holding")
data class HoldingEntity(
    @PrimaryKey
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("ltp")
    val ltp: Double,
    @SerializedName("avgPrice")
    val avgPrice: Double,
    @SerializedName("close")
    val close: Double
)
