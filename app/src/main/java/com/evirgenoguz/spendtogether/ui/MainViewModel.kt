package com.evirgenoguz.spendtogether.ui

import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.model.response.UserResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel(){

    var currentUser: UserResponseModel? = null
        private set

    fun setCurrentUser(user: UserResponseModel){
        currentUser = user
    }
}