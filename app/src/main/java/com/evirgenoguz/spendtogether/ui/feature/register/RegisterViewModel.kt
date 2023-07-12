package com.evirgenoguz.spendtogether.ui.feature.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.ServerErrorModel
import com.evirgenoguz.spendtogether.data.model.request.RegisterRequestModel
import com.evirgenoguz.spendtogether.data.model.response.RegisterResponseModel
import com.evirgenoguz.spendtogether.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {


    private val _register = MutableLiveData<NetworkResult<RegisterResponseModel>>()
    val register: LiveData<NetworkResult<RegisterResponseModel>> = _register

    fun register(registerRequestModel: RegisterRequestModel) {
        viewModelScope.launch {
            _register.postValue(NetworkResult.Loading)
            authRepository.register(registerRequestModel)
                .addOnSuccessListener { authResult ->
                    authResult.user?.uid?.let { uid ->
                        _register.postValue(
                            NetworkResult.Success(
                                RegisterResponseModel(
                                    uid = uid
                                )
                            )
                        )
                    } ?: kotlin.run {
                        _register.postValue(
                            NetworkResult.Error(
                                ServerErrorModel(
                                    "Can not access user data."
                                )
                            )
                        )
                    }
                }
                .addOnFailureListener {
                    _register.postValue(
                        NetworkResult.Error(
                            ServerErrorModel(
                                it.localizedMessage ?: "An error occurred"
                            )
                        )
                    )
                }
        }
    }
}