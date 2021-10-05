package com.kurshin.tvbank.network.model.balance_response

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "response", strict = false)
data class ResponseBalance @JvmOverloads constructor(

    @field:Element(name = "data") @param:Element(name = "data")
    val data: Data
)