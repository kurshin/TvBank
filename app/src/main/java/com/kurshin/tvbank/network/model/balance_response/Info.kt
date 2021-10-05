package com.kurshin.tvbank.network.model.balance_response

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false)
data class Info @JvmOverloads constructor(

    @field:Element(name = "statements", required = false) @param:Element(
        name = "statements",
        required = false
    )
    var statements: Statements? = null,

    @field:Element(name = "cardbalance", required = false) @param:Element(
        name = "cardbalance",
        required = false
    )
    var cardbalance: CardBalance? = null
)