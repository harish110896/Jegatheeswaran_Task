package com.jegatheeswaran.task.data.model

import com.jegatheeswaran.task.data.local.HoldingEntity
import kotlinx.serialization.Serializable

@Serializable
data class HoldingDto(
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
)

fun HoldingDto.toEntity() = HoldingEntity(
    symbol = symbol,
    quantity = quantity,
    ltp = ltp,
    avgPrice = avgPrice,
    close = close
)
