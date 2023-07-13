package com.evirgenoguz.spendtogether.data.model.response

data class TCMBAnlikKurBilgileri(
    val BanknoteBuying: Double,
    val BanknoteSelling: Double,
    val CrossRateOther: Double,
    val CrossRateUSD: Double,
    val CurrencyName: String,
    val ForexBuying: Double,
    val ForexSelling: Double,
    val Isim: String
)