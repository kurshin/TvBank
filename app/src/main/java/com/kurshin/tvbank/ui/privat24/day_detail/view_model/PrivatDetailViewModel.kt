package com.kurshin.tvbank.ui.privat24.day_detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurshin.tvbank.db.entity.BalanceEntity
import com.kurshin.tvbank.usecase.HistoryBalanceUseCase
import com.kurshin.tvbank.util.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PrivatDetailViewModel @Inject constructor(
    private val balanceUseCase: HistoryBalanceUseCase
): ViewModel() {

    private val _balance: MutableLiveData<List<BalanceEntity>> = MutableLiveData()
    val currentBalance: LiveData<List<BalanceEntity>> = _balance

    private val _error: MutableLiveData<String> = MutableLiveData()
    val requestError: LiveData<String> = _error

    fun getBalanceHistory(day1: LocalDate, day2: LocalDate = day1) = launch {
        val balanceList = balanceUseCase.getBalancePerDay(day1, day2)
        _balance.postValue(balanceList)
    }
}