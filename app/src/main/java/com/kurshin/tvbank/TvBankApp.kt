package com.kurshin.tvbank

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.kurshin.tvbank.repository.PreferencesRepository
import com.kurshin.tvbank.util.getAccountInfoFromAsset
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class TvBankApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var prefs: PreferencesRepository

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        if (prefs.accountInfo.userId.isEmpty()) {
            getAccountInfoFromAsset()?.let {
                prefs.accountInfo = it
            }
        }
    }
}