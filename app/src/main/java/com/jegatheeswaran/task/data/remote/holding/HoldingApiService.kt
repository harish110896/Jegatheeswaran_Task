package com.jegatheeswaran.task.data.remote.holding

import com.jegatheeswaran.task.data.model.HoldingsResponse
import com.jegatheeswaran.task.data.remote.ApiConfiguration
import retrofit2.http.GET

interface HoldingApiService {

    @GET(ApiConfiguration.BASE_URL)
    suspend fun fetchHoldings(): HoldingsResponse
}