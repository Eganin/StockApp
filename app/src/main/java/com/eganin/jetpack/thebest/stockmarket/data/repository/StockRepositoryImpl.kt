package com.eganin.jetpack.thebest.stockmarket.data.repository

import com.eganin.jetpack.thebest.stockmarket.data.local.StockDatabase
import com.eganin.jetpack.thebest.stockmarket.data.mapper.toCompanyListing
import com.eganin.jetpack.thebest.stockmarket.data.remote.StockApi
import com.eganin.jetpack.thebest.stockmarket.domain.model.CompanyListing
import com.eganin.jetpack.thebest.stockmarket.domain.repository.StockRepository
import com.eganin.jetpack.thebest.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
) : StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val localListings = dao.searchCompanyListing(query = query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                response.byteStream()
            }catch (e : IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }catch (e : HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
            }
        }
    }
}