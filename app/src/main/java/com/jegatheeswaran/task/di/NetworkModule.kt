package com.jegatheeswaran.task.di

import com.jegatheeswaran.task.data.remote.ApiConfiguration
import com.jegatheeswaran.task.data.remote.ApiConfiguration.TIMEOUT_IN_SECONDS
import com.jegatheeswaran.task.data.remote.HoldingApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideBaseURL(): String {
        return ApiConfiguration.BASE_URL
    }


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        okHttpClient.readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideRestApiService(retrofit: Retrofit): HoldingApiService {
        return retrofit.create(HoldingApiService::class.java)
    }
}