package com.kurshin.tvbank.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kurshin.tvbank.db.entity.BalanceEntity

@Database(
    entities = [
        BalanceEntity::class
    ],
    version = 9
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun privat24Dao(): Privat24Dao
}