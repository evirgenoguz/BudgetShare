package com.evirgenoguz.spendtogether.data.model.request

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

data class RegisterRequestModel(
    val fullName: String,
    val email: String,
    val password: String,
)
