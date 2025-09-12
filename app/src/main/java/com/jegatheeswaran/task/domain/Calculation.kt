package com.jegatheeswaran.task.domain

data class Totals(
    val currentValue: Double,
    val totalInvestment: Double,
    val totalPnl: Double,
    val todayPnl: Double
)

data class SimpleHolding(
    val symbol: String,
    val quantity: Int,
    val ltp: Double,
    val avgPrice: Double,
    val close: Double
)

object Calculation {

    fun currentValue(qty: Int, ltp: Double) = qty * ltp
    fun invested(qty: Int, avg: Double) = qty * avg
    fun todayPnlFor(qty: Int, close: Double, ltp: Double) = (close - ltp) * qty

//    fun aggregate(holdings: List<Holding>): Totals {
//        var cv = 0.0
//        var inv = 0.0
//        var tpn = 0.0
//        var tday = 0.0
//        for (h in holdings) {
//            cv += currentValue(h.quantity, h.ltp)
//            inv += invested(h.quantity, h.avgPrice)
//            tday += todayPnlFor(h.quantity, h.close, h.ltp)
//        }
//        tpn = cv - inv
//        return Totals(cv, inv, tpn, tday)
//    }
}

