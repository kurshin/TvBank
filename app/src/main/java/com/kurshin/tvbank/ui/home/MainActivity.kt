package com.kurshin.tvbank.ui.home

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.kurshin.tvbank.R
import com.kurshin.tvbank.ui.privat.view.PrivatFragment
import com.kurshin.tvbank.util.currentNavigationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.currentNavigationFragment
        if (currentFragment is PrivatFragment) {
            if (currentFragment.onBackPressed()) {
                return
            }
        }
        super.onBackPressed()
    }
}