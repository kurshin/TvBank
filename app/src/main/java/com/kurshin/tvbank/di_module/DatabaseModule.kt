package com.kurshin.tvbank.di_module

import android.content.Context
import androidx.room.Room
import com.kurshin.tvbank.db.AppDatabase
import com.kurshin.tvbank.db.Privat24Dao
import com.kurshin.tvbank.util.isDebug
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        val builder =
            Room.databaseBuilder(context, AppDatabase::class.java, "tvbank-database")
                .allowMainThreadQueries()
        if (isDebug) {
            builder.fallbackToDestructiveMigration()
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun providePrivat24Dao(appDatabase: AppDatabase): Privat24Dao {
        return appDatabase.privat24Dao()
    }
}