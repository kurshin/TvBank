package com.kurshin.tvbank.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.kurshin.tvbank.ui.home.MainActivity

class SplashActivity : AppCompatActivity() {

    private val delay = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(::startMainActivity, delay)
    }

    private fun startMainActivity() {
        if (!isFinishing && !isDestroyed) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}