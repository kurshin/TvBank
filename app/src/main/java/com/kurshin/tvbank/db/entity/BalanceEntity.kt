package com.kurshin.tvbank.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BalanceEntity (

    @PrimaryKey
    var appcode: String = "",

    @ColumnInfo(name = "card")
    var card: String = "",

    @ColumnInfo(name = "trandate")
    var trandate: Long = 0,

    @ColumnInfo(name = "trantime")
    var trantime: Long = 0,

    @ColumnInfo(name = "amount")
    var amount: Double = 0.0,

    @ColumnInfo(name = "terminal")
    var terminal: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "rest")
    var rest: Double = 0.0,

    @ColumnInfo(name = "currency")
    var currency: String = ""
) {
    override fun toString(): String {
        return "BalanceEntity(card='$card', appcode='$appcode', trandate=$trandate, trantime=$trantime, amount=$amount, terminal='$terminal', description='$description', rest=$rest, currency='$currency')"
    }
}
