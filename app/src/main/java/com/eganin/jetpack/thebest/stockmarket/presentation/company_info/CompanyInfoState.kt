package com.eganin.jetpack.thebest.stockmarket.presentation.company_info

import com.eganin.jetpack.thebest.stockmarket.domain.model.CompanyInfo
import com.eganin.jetpack.thebest.stockmarket.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfo: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
