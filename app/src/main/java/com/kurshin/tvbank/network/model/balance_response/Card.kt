package com.kurshin.tvbank.network.model.balance_response

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false)
data class Card @JvmOverloads constructor(

    @field:Element(name = "currency") @param:Element(name = "currency")
    var currency: String = "",

    @field:Element(name = "account") @param:Element(name = "account")
    var account: String = "",

    @field:Element(name = "card_number") @param:Element(name = "card_number")
    var card_number: String = "",

    @field:Element(name = "acc_name", required = false) @param:Element(
        name = "acc_name",
        required = false
    )
    var acc_name: String = "",

    @field:Element(name = "acc_type", required = false) @param:Element(
        name = "acc_type",
        required = false
    )
    var acc_type: String = "",

    @field:Element(name = "card_type", required = false) @param:Element(
        name = "card_type",
        required = false
    )
    var card_type: String = "",

    @field:Element(name = "card_stat", required = false) @param:Element(
        name = "card_stat",
        required = false
    )
    var card_stat: String = "",

    @field:Element(name = "src", required = false) @param:Element(name = "src", required = false)
    var src: String = "",

    @field:Element(name = "main_card_number") @param:Element(name = "main_card_number")
    var main_card_number: String = ""
)