package com.eganin.jetpack.thebest.stockmarket.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey : String= API_KEY
    ) : ResponseBody

    companion object{
        private const val API_KEY = "T7X1WW9R4RM7401T"
        const val BASE_URL = "https://www.alphavantage.co"
    }
}