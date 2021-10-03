package com.kurshin.tvbank.ui.privat.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrivatViewModel @Inject constructor(): ViewModel() {

    fun getSomeData() {
        Log.i("1111", "private")
    }
}