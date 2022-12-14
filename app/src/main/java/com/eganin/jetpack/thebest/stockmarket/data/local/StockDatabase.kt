package com.eganin.jetpack.thebest.stockmarket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CompanyListingEntity::class],
    version = 1
)
abstract class StockDatabase : RoomDatabase() {
    abstract val dao : StockDao
    companion object{
        const val NAME_DATABASE = "stock.db"
    }
}