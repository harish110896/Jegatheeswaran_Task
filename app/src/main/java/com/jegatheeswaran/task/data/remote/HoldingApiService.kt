package com.jegatheeswaran.task.data.remote

import com.jegatheeswaran.task.data.model.HoldingsResponse
import retrofit2.http.GET

interface HoldingApiService {

    @GET(ApiConfiguration.BASE_URL)
    suspend fun fetchHoldings(): HoldingsResponse
}