package com.eganin.jetpack.thebest.stockmarket.data.remote

import com.eganin.jetpack.thebest.stockmarket.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey: String = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getInstradayInfo(
        @Query("symbol") symbol: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): CompanyInfoDto

    companion object {
        private const val API_KEY = "T7X1WW9R4RM7401T"
        const val BASE_URL = "https://www.alphavantage.co"
    }
}