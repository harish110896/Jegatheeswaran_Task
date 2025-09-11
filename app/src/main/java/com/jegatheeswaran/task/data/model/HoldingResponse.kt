package com.jegatheeswaran.task.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HoldingsResponse(val data: Data) {
    @Serializable
    data class Data(val userHolding: List<HoldingDto>)
}