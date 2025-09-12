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

//fun HoldingDto.toEntity() = HoldingEntity(
//    symbol = symbol,
//    quantity = quantity,
//    ltp = ltp,
//    avgPrice = avgPrice,
//    close = close
//)

fun HoldingDto.calculatePnl() : Pair<String, Boolean>{
    val pnl = (ltp * quantity) - (close * quantity)
    val isProfit = pnl >= 0
    val pnlDisplayString = pnl.toSignedRupeeString(isProfit)
    return Pair(pnlDisplayString, isProfit)
}
