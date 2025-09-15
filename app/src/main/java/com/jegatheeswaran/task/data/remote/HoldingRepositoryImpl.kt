package com.jegatheeswaran.task.data.remote

import com.jegatheeswaran.task.data.model.HoldingDto
import com.jegatheeswaran.task.utils.network.ApiResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HoldingRepositoryImpl @Inject constructor(
    private val holdingApiService: HoldingApiService
) : HoldingRepository {

    override suspend fun fetchHolding(): List<HoldingDto> {
        val response = holdingApiService.fetchHoldings()
        return response.data.userHolding
    }

    override suspend fun fetchHoldingList(): Flow<ApiResponseState<List<HoldingDto>>> = flow {
        emit(ApiResponseState.Loading)
        try {
            val result = holdingApiService.fetchHoldings()
            emit(ApiResponseState.Success(result.data.userHolding))
        } catch (e: Exception) {
            emit(ApiResponseState.Error(e))
        }
    }
}