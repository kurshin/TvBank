package com.kurshin.tvbank.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kurshin.tvbank.db.entity.ReportEntity

@Dao
interface Privat24Dao {

    @Insert
    suspend fun add(entity: ReportEntity)

    @Query("SELECT * FROM ReportEntity ORDER BY created_at DESC")
    fun getAllPinsLiveData(): LiveData<List<ReportEntity>>

    @Query("SELECT * FROM ReportEntity where server_id = 0")
    suspend fun getAllNotReportedPins(): List<ReportEntity>

    @Query("SELECT EXISTS(SELECT * FROM ReportEntity WHERE server_id = :serverId)")
    fun isRowIsExist(serverId : Long) : Boolean

    @Update
    suspend fun update(entity: ReportEntity)

    @Query("DELETE FROM ReportEntity")
    suspend fun deleteAll()
}