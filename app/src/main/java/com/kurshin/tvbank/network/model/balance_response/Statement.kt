package com.kurshin.tvbank.network.model.balance_response

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
)