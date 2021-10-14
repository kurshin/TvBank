package com.kurshin.tvbank.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.kurshin.tvbank.util.json
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(
    prefs: SharedPreferences
) {
    var accountInfo: AccountInfo by prefs.json(Gson(), AccountInfo(
        "",
        "",
        "",
    ))

    var currentBalance: CurrentBalance by prefs.json(Gson(), CurrentBalance(
        "0.0",
        "UAH",
        0
    ))
}

data class AccountInfo(
    val password: String,
    val userId: String,
    val cardNumber: String,
): Serializable

data class CurrentBalance(
    val balance: String,
    val currency: String,
    val updatedAt: Long
): Serializable