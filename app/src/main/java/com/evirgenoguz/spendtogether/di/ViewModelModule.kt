package com.evirgenoguz.spendtogether.di

import com.evirgenoguz.spendtogether.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {


    @Provides
    @ViewModelScoped
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository = AuthRepository(firebaseAuth)





}