package com.kurshin.tvbank.ui.privat24.calendar.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kurshin.tvbank.network.BinaryResult
import com.kurshin.tvbank.network.model.balance_response.Data
import com.kurshin.tvbank.repository.NetworkRepository
import com.kurshin.tvbank.util.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrivatViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
): ViewModel() {

    private val _balance: MutableLiveData<Data> = MutableLiveData()
    val currentBalance: LiveData<Data> = _balance

    private val _error: MutableLiveData<String> = MutableLiveData()
    val requestError: LiveData<String> = _error

    fun getCurrentBalance() = launch {
        when (val result = networkRepository.getCurrentBalance()) {
            is BinaryResult.Success -> {
                _balance.postValue(result.data.data)
            }
            is BinaryResult.Error -> {
                _error.postValue(result.message)
            }
        }
    }
}