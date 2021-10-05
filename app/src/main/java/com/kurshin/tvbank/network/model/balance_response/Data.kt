package com.kurshin.tvbank.network.model.balance_response

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false)
data class Data @JvmOverloads constructor(

    @field:Element(name = "oper") @param:Element(name = "oper")
    val oper: String,

    @field:Element(name = "info") @param:Element(name = "info")
    val info: Info
)