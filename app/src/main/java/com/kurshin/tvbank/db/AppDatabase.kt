package com.kurshin.tvbank.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kurshin.tvbank.db.entity.ReportEntity

@Database(
    entities = [
        ReportEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun privat24Dao(): Privat24Dao
}