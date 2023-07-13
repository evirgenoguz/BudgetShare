package com.evirgenoguz.spendtogether.data.service.api

import com.evirgenoguz.spendtogether.data.model.response.CurrencyResponse
import com.evirgenoguz.spendtogether.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: Oguz Evirgen
 * @Date: 13.07.2023
 */

interface CurrencyApi {

    @GET(ENDPOINT_GET_CURRENCY)
    suspend fun getCurrency(@Query(QUERY_API) apiKey: String = API_KEY): CurrencyResponse

    companion object{
        const val ENDPOINT_GET_CURRENCY = "v1/latest"

        const val QUERY_API = "apikey"
    }
}