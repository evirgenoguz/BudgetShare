package com.evirgenoguz.spendtogether.data.service

import com.evirgenoguz.spendtogether.data.model.request.LoginRequestModel
import com.evirgenoguz.spendtogether.data.model.request.RegisterRequestModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

interface AuthService{
    fun register(registerRequestModel: RegisterRequestModel): Task<AuthResult>

    fun login(loginRequestModel: LoginRequestModel): Task<AuthResult>
}