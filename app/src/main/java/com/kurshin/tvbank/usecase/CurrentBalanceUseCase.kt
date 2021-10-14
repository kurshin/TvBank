package com.kurshin.tvbank.usecase

import com.kurshin.tvbank.network.BinaryResult
import com.kurshin.tvbank.repository.CurrentBalance
import com.kurshin.tvbank.repository.NetworkRepository
import com.kurshin.tvbank.repository.PreferencesRepository
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import javax.inject.Inject

private const val HOURS_CACHED = 1

class CurrentBalanceUseCase @Inject constructor(
    private val network: NetworkRepository,
    private val prefs: PreferencesRepository
) {

    suspend fun getCurrentBalance(): CurrentBalance {
        val balanceUpdateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(prefs.currentBalance.updatedAt), ZoneId.systemDefault())
        if (ChronoUnit.HOURS.between(balanceUpdateTime, LocalDateTime.now()) > HOURS_CACHED) {
            when (val result = network.getCurrentBalance()) {
                is BinaryResult.Success -> {
                    val networkBalance = result.data.data.toCurrentBalance()
                    prefs.currentBalance = networkBalance
                }
            }
        }

        return prefs.currentBalance
    }
}