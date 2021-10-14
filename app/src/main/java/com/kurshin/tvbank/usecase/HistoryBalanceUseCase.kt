package com.kurshin.tvbank.usecase

import android.util.Log
import com.kurshin.tvbank.db.Privat24Dao
import com.kurshin.tvbank.db.entity.BalanceEntity
import com.kurshin.tvbank.network.BinaryResult.Success
import com.kurshin.tvbank.repository.NetworkRepository
import com.kurshin.tvbank.util.toPrivatRequestStr
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class HistoryBalanceUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val privatDao: Privat24Dao
) {

    suspend fun getBalancePerDay(date: LocalDate, dateEnd: LocalDate): List<BalanceEntity> {
        val longDate = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val longDateEnd = dateEnd.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        var result = privatDao.getBalancePerDate(longDate, longDateEnd)

        if (result.isEmpty()) {
            when (val httpResult = networkRepository.getBalanceHistory(date.toPrivatRequestStr(), dateEnd.toPrivatRequestStr())) {
                is Success -> {
                    result = httpResult.data.data.toBalanceEntityList()
                    privatDao.addAll(result)
                }
                else -> {
                    Log.i("1111", "getBalanceHistory error ${date.toPrivatRequestStr()}")
                }
            }
        }

        return result
    }
}