package com.evirgenoguz.spendtogether.data.model.request

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

data class UserRequest(
    val fullName: String,
    val email: String,
    val groups: List<String>? = null
)
