package com.evirgenoguz.spendtogether.data.service.api

import com.evirgenoguz.spendtogether.data.model.response.CurrencyResponse
import retrofit2.http.GET

/**
 * @Author: Oguz Evirgen
 * @Date: 13.07.2023
 */

interface CurrencyApi {

    @GET(ENDPOINT_GET_CURRENCY)
    suspend fun getCurrency(): CurrencyResponse

    companion object{
        const val ENDPOINT_GET_CURRENCY = "kurgetir"
    }
}