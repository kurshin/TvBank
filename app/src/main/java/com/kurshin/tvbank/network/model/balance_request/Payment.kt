package com.kurshin.tvbank.network.model.balance_request

import org.simpleframework.xml.ElementList

data class Payment @JvmOverloads constructor(

    @field:ElementList(name = "prop") @param:ElementList(name = "prop")
    val prop: List<Prop>
)