package com.eganin.jetpack.thebest.stockmarket.presentation.company_listings

import com.eganin.jetpack.thebest.stockmarket.domain.model.CompanyListing

data class CompanyListingsState(
    val companies : List<CompanyListing> = emptyList(),
    val isLoading : Boolean=false,
    val isRefreshing :Boolean =false,
    val searchQuery : String = "",
    val error : String? = null
)
