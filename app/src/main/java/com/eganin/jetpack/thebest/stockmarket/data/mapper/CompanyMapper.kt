package com.eganin.jetpack.thebest.stockmarket.data.mapper

import com.eganin.jetpack.thebest.stockmarket.data.local.CompanyListingEntity
import com.eganin.jetpack.thebest.stockmarket.data.remote.dto.CompanyInfoDto
import com.eganin.jetpack.thebest.stockmarket.domain.model.CompanyInfo
import com.eganin.jetpack.thebest.stockmarket.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing() : CompanyListing{
    return CompanyListing(
        name = name,
        symbol=symbol,
        exchange=exchange
    )
}

fun CompanyListing.toCompanyListingEntity() : CompanyListingEntity{
    return CompanyListingEntity(
        name = name,
        symbol=symbol,
        exchange=exchange
    )
}

fun CompanyInfoDto.toCompanyInfo() : CompanyInfo{
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name= name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}