package com.jegatheeswaran.task.di

import android.app.Application
import androidx.room.Room
import com.jegatheeswaran.task.data.local.HoldingDb
import com.jegatheeswaran.task.data.local.HoldingEntity.Companion.TABLE_NAME
import com.jegatheeswaran.task.data.remote.HoldingApi
import com.jegatheeswaran.task.data.repo.HoldingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDb(app: Application) = Room.databaseBuilder(
        app, HoldingDb::class.java, "$TABLE_NAME.db"
    ).fallbackToDestructiveMigration().build()


    @Provides
    fun provideDao(db: HoldingDb) = db.dao()

    @Provides
    @Singleton
    fun provideRepo(api: HoldingApi, db: HoldingDb) = HoldingRepository(api, db.dao())
}
