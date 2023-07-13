package com.evirgenoguz.spendtogether.data.model.response

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("data")
    val ourData: Data
)