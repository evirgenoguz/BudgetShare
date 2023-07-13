package com.evirgenoguz.spendtogether.data.repository

import com.evirgenoguz.spendtogether.data.NetworkManager
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.model.response.CurrencyResponse
import com.evirgenoguz.spendtogether.data.service.api.CurrencyApi
import javax.inject.Inject

/**
 * @Author: Oguz Evirgen
 * @Date: 13.07.2023
 */

class CurrencyRepository @Inject constructor(
    private val apiService: CurrencyApi,
    private val networkManager: NetworkManager
) {

    suspend fun getCurrency(): NetworkResult<CurrencyResponse> {
        return networkManager.makeRequest {
            apiService.getCurrency()
        }
    }
}