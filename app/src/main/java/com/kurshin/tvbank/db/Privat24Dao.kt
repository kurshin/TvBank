package com.kurshin.tvbank.db

import androidx.room.*
import com.kurshin.tvbank.db.entity.BalanceEntity

@Dao
interface Privat24Dao {

    @Insert
    suspend fun add(entity: BalanceEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(entityList: List<BalanceEntity>): List<Long>

    @Query("SELECT * FROM BalanceEntity WHERE trandate >= :date AND trandate <= :dateEnd ORDER BY trantime")
    suspend fun getBalancePerDate(date: Long, dateEnd: Long): List<BalanceEntity>

    @Update
    suspend fun update(entity: BalanceEntity)

    @Query("DELETE FROM BalanceEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM BalanceEntity  WHERE trandate = :date")
    suspend fun deleteDate(date: Long)
}