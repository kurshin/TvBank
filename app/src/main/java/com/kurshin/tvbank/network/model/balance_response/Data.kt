package com.kurshin.tvbank.network.model.balance_response

import com.kurshin.tvbank.db.entity.BalanceEntity
import com.kurshin.tvbank.repository.CurrentBalance
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.time.LocalDateTime
import java.time.ZoneId

@Root(strict = false)
data class Data @JvmOverloads constructor(

    @field:Element(name = "oper" , required = false) @param:Element(name = "oper", required = false)
    val oper: String = "",

    @field:Element(name = "info", required = false) @param:Element(name = "info", required = false)
    val info: Info = Info(null, null),

    @field:Element(name = "error message" , required = false) @param:Element(name = "error message", required = false)
    val errorMessage: String = ""
) {
    fun toBalanceEntityList(): List<BalanceEntity> {
        val result = mutableListOf<BalanceEntity>()
        info.statements?.statement?.forEach {
            result.add(it.toBalanceEntity())
        }
        return result
    }

    fun toCurrentBalance(): CurrentBalance {
        val currentTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        return CurrentBalance(info.cardbalance?.balance ?: "0.0", info?.cardbalance?.card?.currency ?: "[?]", currentTime)
    }
}