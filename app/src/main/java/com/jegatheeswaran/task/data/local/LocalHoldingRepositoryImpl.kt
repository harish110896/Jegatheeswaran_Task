package com.jegatheeswaran.task.data.local

import com.jegatheeswaran.task.data.local.dao.HoldingDao
import com.jegatheeswaran.task.data.model.HoldingEntity
import javax.inject.Inject

class LocalHoldingRepositoryImpl @Inject constructor(
    private val holdingDao: HoldingDao,
) : LocalHoldingRepository {
    override suspend fun loadHolding(): List<HoldingEntity> {
        return holdingDao.getAllHoldingListing()
    }

    override suspend fun insertAll(holding: List<HoldingEntity>) {
        holdingDao.insertAll(holding)
    }
}