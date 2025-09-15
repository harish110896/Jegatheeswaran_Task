package com.jegatheeswaran.task.data.model

import com.google.gson.annotations.SerializedName
import com.jegatheeswaran.task.utils.toSignedRupeeString

data class HoldingDto(
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

fun HoldingDto.calculatePnl() : Pair<String, Boolean>{
    val pnl = (ltp * quantity) - (close * quantity)
    val positive = pnl >= 0
    val displayString = pnl.toSignedRupeeString(positive)
    return Pair(displayString, positive)
}

fun List<HoldingDto>.calculateCurrentValue() : Pair<String, Boolean>{
    val currentValue: Double = this.sumOf { it.ltp * it.quantity }
    val positive = currentValue >= 0
    val displayString = currentValue.toSignedRupeeString(positive)
    return Pair(displayString, positive)
}

fun List<HoldingDto>.calculateTotalInvestment() : Pair<String, Boolean>{
    val totalInvestment: Double = this.sumOf { it.avgPrice * it.quantity }
    val positive = totalInvestment >= 0
    val displayString = totalInvestment.toSignedRupeeString(positive)
    return Pair(displayString, positive)
}


fun List<HoldingDto>.calculateTotalPnl() : Pair<String, Boolean>{
    val currentValue: Double = this.sumOf { it.ltp * it.quantity }
    val totalInvestment: Double = this.sumOf { it.avgPrice * it.quantity }
    val totalPnl: Double = currentValue - totalInvestment
    val positive = totalPnl >= 0
    val displayString = totalPnl.toSignedRupeeString(positive)
    return Pair(displayString, positive)
}

fun List<HoldingDto>.calculateTodayPnl() : Pair<String, Boolean>{
    val todayPnl: Double =  this.sumOf { (it.close - it.ltp) * it.quantity }
    val positive = todayPnl >= 0
    val displayString = todayPnl.toSignedRupeeString(positive)
    return Pair(displayString, positive)
}

fun List<HoldingDto>.toEntityList(): List<HoldingEntity> =
    map { dto ->
        HoldingEntity(
            symbol = dto.symbol,
            quantity = dto.quantity,
            ltp = dto.ltp,
            avgPrice = dto.avgPrice,
            close = dto.close
        )
    }

fun List<HoldingEntity>.toDtoList(): List<HoldingDto> =
    map { entity ->
        HoldingDto(
            symbol = entity.symbol,
            quantity = entity.quantity,
            ltp = entity.ltp,
            avgPrice = entity.avgPrice,
            close = entity.close
        )
    }
