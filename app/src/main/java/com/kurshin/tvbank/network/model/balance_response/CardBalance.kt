package com.kurshin.tvbank.network.model.balance_response

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false)
data class CardBalance @JvmOverloads constructor(

    @field:Element(name = "av_balance") @param:Element(name = "av_balance")
    var avBalance: String = "",

    @field:Element(name = "bal_date") @param:Element(name = "bal_date")
    var balDate: String = "",

    @field:Element(name = "bal_dyn") @param:Element(name = "bal_dyn")
    var balDyn: String? = "",

    @field:Element(name = "balance") @param:Element(name = "balance")
    var balance: String = "",

    @field:Element(name = "fin_limit") @param:Element(name = "fin_limit")
    var finLimit: String = "",

    @field:Element(name = "trade_limit") @param:Element(name = "trade_limit")
    var tradeLimit: String = "",

    @field:Element(name = "card") @param:Element(name = "card")
    val card: Card
)