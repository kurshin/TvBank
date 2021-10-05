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
}

data class AccountInfo(
    val password: String,
    val userId: String,
    val cardNumber: String,
): Serializable