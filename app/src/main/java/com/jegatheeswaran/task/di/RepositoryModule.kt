package com.jegatheeswaran.task.di

import com.jegatheeswaran.task.data.remote.HoldingApiService
import com.jegatheeswaran.task.data.remote.HoldingRepository
import com.jegatheeswaran.task.data.remote.HoldingRepositoryImpl
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