package com.jegatheeswaran.task.data.remote

import com.jegatheeswaran.task.data.model.HoldingsResponse
import com.jegatheeswaran.task.di.NetworkModule.BASE_URL
import retrofit2.http.GET

interface HoldingApi {

    @GET(BASE_URL)
    suspend fun fetchHoldings(): HoldingsResponse
}