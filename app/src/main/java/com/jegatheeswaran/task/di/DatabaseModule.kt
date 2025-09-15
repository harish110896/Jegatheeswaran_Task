package com.jegatheeswaran.task.di

import android.content.Context
import androidx.room.Room
import com.jegatheeswaran.task.data.local.LocalHoldingRepositoryImpl
import com.jegatheeswaran.task.data.local.dao.HoldingDao
import com.jegatheeswaran.task.data.local.db.HoldingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): HoldingDatabase {
        return Room.databaseBuilder(
            context,
            HoldingDatabase::class.java,
            "holding.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideHoldingDao(holdingDatabase: HoldingDatabase): HoldingDao =
        holdingDatabase.getHoldingDatabase()

    @Singleton
    @Provides
    fun provideLocalHoldingRepository(holdingDao: HoldingDao): LocalHoldingRepositoryImpl =
         LocalHoldingRepositoryImpl(
            holdingDao
        )
}