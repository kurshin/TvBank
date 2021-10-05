package com.kurshin.tvbank.network.model.balance_request

import org.simpleframework.xml.Element

data class Merchant @JvmOverloads constructor(

    @field:Element(name = "id") @param:Element(name = "id")
    val id: String,

    @field:Element(name = "signature") @param:Element(name = "signature")
    val signature: String
)