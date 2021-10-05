package com.kurshin.tvbank.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ReportEntity (

    @PrimaryKey(autoGenerate = true)
    var _id: Long = 0,

    @ColumnInfo(name = "created_at")
    var createdAt: Long = Date().time,

    @ColumnInfo(name = "server_id")
    var serverId: Long = 0,

    @ColumnInfo(name = "type_id")
    var typeId: Int = 0,

    @ColumnInfo(name = "type_name")
    var typeName: String = ""
)
