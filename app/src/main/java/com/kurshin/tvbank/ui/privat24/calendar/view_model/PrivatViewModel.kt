package com.kurshin.tvbank.ui.privat24.calendar.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurshin.tvbank.repository.CurrentBalance
import com.kurshin.tvbank.usecase.CurrentBalanceUseCase
import com.kurshin.tvbank.util.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrivatViewModel @Inject constructor(
    private val balanceUseCase: CurrentBalanceUseCase
): ViewModel() {

    private val _balance: MutableLiveData<CurrentBalance> = MutableLiveData()
    val currentBalance: LiveData<CurrentBalance> = _balance

    private val _error: MutableLiveData<String> = MutableLiveData()
    val requestError: LiveData<String> = _error

    fun getCurrentBalance() = launch {
        val value = balanceUseCase.getCurrentBalance()
        _balance.postValue(value)
    }
}