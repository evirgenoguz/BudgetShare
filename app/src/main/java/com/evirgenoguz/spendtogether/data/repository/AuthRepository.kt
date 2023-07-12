package com.evirgenoguz.spendtogether.data.repository

import com.evirgenoguz.spendtogether.data.model.request.LoginRequestModel
import com.evirgenoguz.spendtogether.data.model.request.RegisterRequestModel
import com.evirgenoguz.spendtogether.data.service.AuthService
import com.google.firebase.auth.FirebaseAuth


/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

class AuthRepository(
    private val firebaseAuth: FirebaseAuth
): AuthService {
    override fun register(registerRequestModel: RegisterRequestModel) =
        firebaseAuth.createUserWithEmailAndPassword(
            registerRequestModel.email,
            registerRequestModel.password
        )

    override fun login(loginRequestModel: LoginRequestModel) =
        firebaseAuth.signInWithEmailAndPassword(loginRequestModel.email, loginRequestModel.password)
}