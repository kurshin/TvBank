package com.kurshin.tvbank.network.model.balance_response

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
data class Statements @JvmOverloads constructor(

    @field:Attribute(name = "status") @param:Attribute(name = "status")
    val status: String,

    @field:Attribute(name = "credit") @param:Attribute(name = "credit")
    val credit: String,

    @field:Attribute(name = "debet") @param:Attribute(name = "debet")
    val debet: String,

    @field:ElementList(
        name = "statement",
        inline = true,
        required = false
    ) @param:ElementList(name = "statement", inline = true, required = false)
    var statement: List<Statement> = emptyList()
)