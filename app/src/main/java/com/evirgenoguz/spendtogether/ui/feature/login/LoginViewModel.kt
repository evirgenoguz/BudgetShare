package com.evirgenoguz.spendtogether.ui.feature.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.ServerErrorModel
import com.evirgenoguz.spendtogether.data.local.SharedPrefManager
import com.evirgenoguz.spendtogether.data.model.request.LoginRequestModel
import com.evirgenoguz.spendtogether.data.model.response.LoginResponseModel
import com.evirgenoguz.spendtogether.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sharedPrefManager: SharedPrefManager
) : BaseViewModel() {

    private val _login = MutableLiveData<NetworkResult<LoginResponseModel>>()
    val login: LiveData<NetworkResult<LoginResponseModel>> = _login

     fun login(loginRequestModel: LoginRequestModel) {
        viewModelScope.launch {
            _login.postValue(NetworkResult.Loading)
            authRepository.login(loginRequestModel)
                .addOnSuccessListener { authResult ->
                    authResult.user?.let { user ->
                        val uid = user.uid
                        _login.postValue(NetworkResult.Success(LoginResponseModel(uid = uid)))
                    } ?: kotlin.run {
                        _login.postValue(
                            NetworkResult.Error(
                                ServerErrorModel(
                                    "Cannot access user data."
                                )
                            )
                        )
                    }
                }
                .addOnFailureListener {
                    _login.postValue(
                        NetworkResult.Error(
                            ServerErrorModel(
                                it.localizedMessage ?: "An error occurred!"
                            )
                        )
                    )
                }
        }
        Patterns.EMAIL_ADDRESS
    }

    fun saveUserUidToSharedPref(uid: String){
        sharedPrefManager.setUId(uid)
    }

    fun getUserUidFromSharedPref() = sharedPrefManager.getUId()
}