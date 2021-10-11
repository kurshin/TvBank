package com.kurshin.tvbank.network.model.balance_response

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false)
data class Data @JvmOverloads constructor(

    @field:Element(name = "oper" , required = false) @param:Element(name = "oper", required = false)
    val oper: String = "",

    @field:Element(name = "info", required = false) @param:Element(name = "info", required = false)
    val info: Info = Info(null, null),

    @field:Element(name = "error message" , required = false) @param:Element(name = "error message", required = false)
    val errorMessage: String = ""
)