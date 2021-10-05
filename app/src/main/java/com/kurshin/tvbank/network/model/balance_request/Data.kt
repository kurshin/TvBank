package com.kurshin.tvbank.network.model.balance_request

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList

data class Data @JvmOverloads constructor(

    @field:Element(name = "oper") @param:Element(name = "oper")
    val oper: String,

    @field:ElementList(name = "payment") @param:ElementList(name = "payment")
    val payment: List<Prop>,

    @field:Element(name = "test") @param:Element(name = "test")
    val test: String,

    @field:Element(name = "wait") @param:Element(name = "wait")
    val wait: String
)