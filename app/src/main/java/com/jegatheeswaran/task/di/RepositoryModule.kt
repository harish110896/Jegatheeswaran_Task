package com.jegatheeswaran.task.di

import com.jegatheeswaran.task.data.remote.holding.HoldingApiService
import com.jegatheeswaran.task.data.remote.holding.HoldingRepository
import com.jegatheeswaran.task.data.remote.holding.HoldingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHoldingRepository(
        apiService: HoldingApiService,
    ): HoldingRepository {
        return HoldingRepositoryImpl(
            apiService
        )
    }
}