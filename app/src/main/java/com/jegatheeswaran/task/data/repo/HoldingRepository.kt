package com.jegatheeswaran.task.data.repo

import com.jegatheeswaran.task.data.local.HoldingDao
import com.jegatheeswaran.task.data.local.toDomain
import com.jegatheeswaran.task.data.model.Holding
import com.jegatheeswaran.task.data.model.toEntity
import com.jegatheeswaran.task.data.remote.HoldingApi

class HoldingRepository(
    private val api: HoldingApi,
    private val dao: HoldingDao
) {
    /**
     * Offline‑first: read DB; if empty or caller requests refresh, fetch network then cache.
     */
    suspend fun getHolding(refresh: Boolean = false): List<Holding> {
        val cached = if (!refresh) dao.getAll() else emptyList()
        return if (cached.isNotEmpty()) cached.map { it.toDomain() } else {
            val response = api.fetchHoldings()
            val entities = response.data.userHolding.map { it.toEntity() }
            dao.clear()
            dao.insertAllHolding(entities)
            entities.map { it.toDomain() }
        }
    }
}