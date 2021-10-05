package com.kurshin.tvbank.network.model.balance_request

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "request", strict = true)
data class RequestBalance @JvmOverloads constructor(

    @field:Attribute(name = "version") @param:Attribute(name = "version")
    var version: String = "",

    @field:Element(name = "merchant") @param:Element(name = "merchant")
    var merchant: Merchant? = null,

    @field:Element(name = "data") @param:Element(name = "data")
    var data: Data? = null
)