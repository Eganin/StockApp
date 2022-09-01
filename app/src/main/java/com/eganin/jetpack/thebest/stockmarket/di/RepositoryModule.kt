package com.eganin.jetpack.thebest.stockmarket.di

import com.eganin.jetpack.thebest.stockmarket.data.csv.CSVParser
import com.eganin.jetpack.thebest.stockmarket.data.csv.CompanyListingsParser
import com.eganin.jetpack.thebest.stockmarket.data.csv.IntradayInfoParser
import com.eganin.jetpack.thebest.stockmarket.data.repository.StockRepositoryImpl
import com.eganin.jetpack.thebest.stockmarket.domain.model.CompanyListing
import com.eganin.jetpack.thebest.stockmarket.domain.model.IntradayInfo
import com.eganin.jetpack.thebest.stockmarket.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser,
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser,
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl,
    ): StockRepository
}