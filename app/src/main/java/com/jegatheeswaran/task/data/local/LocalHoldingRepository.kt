package com.jegatheeswaran.task.data.local

import com.jegatheeswaran.task.data.model.HoldingEntity

interface LocalHoldingRepository {
    suspend fun loadHolding(): List<HoldingEntity>

    suspend fun insertAll(holding: List<HoldingEntity>)
}