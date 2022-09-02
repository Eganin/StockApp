package com.eganin.jetpack.thebest.stockmarket.data.csv

import android.util.Log
import com.eganin.jetpack.thebest.stockmarket.domain.model.CompanyListing
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyListingsParser @Inject constructor() : CSVParser<CompanyListing> {

    override suspend fun parse(stream: InputStream): List<CompanyListing> {
        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader.readAll().drop(n = 1).mapNotNull { line ->
                val symbol = line.getOrNull(index = 0)
                val name = line.getOrNull(index = 1)
                val exchange = line.getOrNull(index = 2)
                CompanyListing(
                    name = name ?: return@mapNotNull null,
                    symbol = symbol ?: return@mapNotNull null,
                    exchange = exchange ?: return@mapNotNull null
                )
            }.also {
                csvReader.close()
            }
        }
    }
}