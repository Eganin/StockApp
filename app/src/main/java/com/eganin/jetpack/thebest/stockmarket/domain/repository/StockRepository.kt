package com.eganin.jetpack.thebest.stockmarket.domain.repository

import com.eganin.jetpack.thebest.stockmarket.domain.model.CompanyListing
import com.eganin.jetpack.thebest.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote : Boolean,
        query : String,
    ): Flow<Resource<List<CompanyListing>>>
}