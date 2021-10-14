package com.kurshin.tvbank.network.model.balance_response

import com.kurshin.tvbank.db.entity.BalanceEntity
import com.kurshin.tvbank.util.toTranDate
import com.kurshin.tvbank.util.toTranTime
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false)
data class Statement @JvmOverloads constructor(

    @field:Attribute(name = "card") @param:Attribute(name = "card")
    val card: String,

    @field:Attribute(name = "appcode") @param:Attribute(name = "appcode")
    val appcode: String,

    @field:Attribute(name = "trandate") @param:Attribute(name = "trandate")
    val trandate: String,

    @field:Attribute(name = "trantime") @param:Attribute(name = "trantime")
    val trantime: String,

    @field:Attribute(name = "amount") @param:Attribute(name = "amount")
    val amount: String,

    @field:Attribute(name = "cardamount") @param:Attribute(name = "cardamount")
    val cardamount: String,

    @field:Attribute(name = "rest") @param:Attribute(name = "rest")
    val rest: String,

    @field:Attribute(name = "terminal") @param:Attribute(name = "terminal")
    val terminal: String,

    @field:Attribute(name = "description") @param:Attribute(name = "description")
    val description: String
) {


    fun toBalanceEntity(): BalanceEntity {
        return BalanceEntity().also {
            it.currency = cardamount.substringAfterLast(" ")
            it.card = card
            it.appcode = appcode
            it.trandate = trandate.toTranDate()
            it.trantime = trantime.toTranTime(trandate)
            it.amount = cardamount.replace(" ${it.currency}", "").toDouble().round()
            it.terminal = terminal
            it.description = description
            it.rest = rest.replace(" ${it.currency}", "").toDouble().round()
        }
    }
}

fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()