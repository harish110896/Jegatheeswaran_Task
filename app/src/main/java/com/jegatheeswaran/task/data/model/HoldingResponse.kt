package com.jegatheeswaran.task.data.model

data class HoldingsResponse(val data: Data) {
    data class Data(val userHolding: List<HoldingDto>)
}