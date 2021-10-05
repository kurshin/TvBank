package com.kurshin.tvbank.network.model.balance_request

import org.simpleframework.xml.Attribute

data class Prop @JvmOverloads constructor(

    @field:Attribute(name = "name") @param:Attribute(name = "name")
    val name: String,

    @field:Attribute(name = "value") @param:Attribute(name = "value")
    val value: String
)