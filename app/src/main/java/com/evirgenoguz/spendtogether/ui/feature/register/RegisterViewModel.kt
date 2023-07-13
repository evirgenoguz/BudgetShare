package com.evirgenoguz.spendtogether.ui.feature.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.ServerErrorModel
import com.evirgenoguz.spendtogether.data.local.SharedPrefManager
import com.evirgenoguz.spendtogether.data.model.request.RegisterRequestModel
import com.evirgenoguz.spendtogether.data.model.request.UserRequestModel
import com.evirgenoguz.spendtogether.data.model.response.RegisterResponseModel
import com.evirgenoguz.spendtogether.data.repository.AuthRepository
import com.evirgenoguz.spendtogether.data.repository.FirestoreRepository.Companion.USER_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val fireStore: FirebaseFirestore,
    private val sharedPrefManager: SharedPrefManager
) : BaseViewModel() {


    private val _register = MutableLiveData<NetworkResult<RegisterResponseModel>>()
    val register: LiveData<NetworkResult<RegisterResponseModel>> = _register



    fun register(registerRequestModel: RegisterRequestModel) {
        viewModelScope.launch {
            _register.postValue(NetworkResult.Loading)
            authRepository.register(registerRequestModel)
                .addOnSuccessListener { authResult ->
                    authResult.user?.uid?.let { uid ->
                        saveUserInfo(uid, UserRequestModel(registerRequestModel.fullName, registerRequestModel.email))
                    } ?:run {
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

    private fun saveUserInfo(userUid: String, user: UserRequestModel){
        fireStore.collection(USER_COLLECTION)
            .document(userUid)
            .set(user)
            .addOnSuccessListener {
                _register.postValue(
                    NetworkResult.Success(
                        RegisterResponseModel(
                            uid = userUid
                        )
                    )
                )
            }.addOnFailureListener {
                _register.postValue(
                    NetworkResult.Error(
                        ServerErrorModel(
                            it.localizedMessage ?: "An error occurred"
                        )
                    )
                )
            }
    }

    fun saveUserUidToSharedPref(uid: String){
        sharedPrefManager.setUId(uid)
    }

    fun getUserUidFromSharedPref() = sharedPrefManager.getUId()

}