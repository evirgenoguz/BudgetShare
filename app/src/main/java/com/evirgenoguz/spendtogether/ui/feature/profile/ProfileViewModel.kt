package com.evirgenoguz.spendtogether.ui.feature.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.local.SharedPrefManager
import com.evirgenoguz.spendtogether.data.model.response.CurrencyResponse
import com.evirgenoguz.spendtogether.data.repository.CurrencyRepository
import com.evirgenoguz.spendtogether.data.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val sharedPrefManager: SharedPrefManager,
    private val currencyRepository: CurrencyRepository
) : BaseViewModel() {

    private val _currency = MutableLiveData<NetworkResult<CurrencyResponse>>()
    val currency: LiveData<NetworkResult<CurrencyResponse>> = _currency

    init {
        getCurrency()
    }

    private fun getCurrency() {
        viewModelScope.launch {
            _currency.postValue(NetworkResult.Loading)
            val result = currencyRepository.getCurrency()
            _currency.postValue(result)
        }
    }

    fun logout(){
        sharedPrefManager.setUId("")
    }

}