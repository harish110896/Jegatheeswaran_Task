package com.jegatheeswaran.task.data.remote.holding

import com.jegatheeswaran.task.data.model.HoldingDto
import com.jegatheeswaran.task.utils.network.ApiResponseState
import kotlinx.coroutines.flow.Flow

interface HoldingRepository {
    suspend fun fetchHolding(): List<HoldingDto>
    suspend fun fetchHoldingList(): Flow<ApiResponseState<List<HoldingDto>>>
}