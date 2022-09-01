package com.eganin.jetpack.thebest.stockmarket.di

import android.app.Application
import androidx.room.Room
import com.eganin.jetpack.thebest.stockmarket.data.local.StockDatabase
import com.eganin.jetpack.thebest.stockmarket.data.local.StockDatabase.Companion.NAME_DATABASE
import com.eganin.jetpack.thebest.stockmarket.data.remote.StockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            NAME_DATABASE
        ).build()
    }
}