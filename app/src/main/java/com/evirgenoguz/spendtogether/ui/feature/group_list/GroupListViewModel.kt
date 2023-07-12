package com.evirgenoguz.spendtogether.ui.feature.group_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.ServerErrorModel
import com.evirgenoguz.spendtogether.data.local.SharedPrefManager
import com.evirgenoguz.spendtogether.data.model.request.CreateGroupRequestModel
import com.evirgenoguz.spendtogether.data.model.response.GroupResponseModel
import com.evirgenoguz.spendtogether.data.model.response.UserResponseModel
import com.evirgenoguz.spendtogether.data.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val sharedPrefManager: SharedPrefManager
) : BaseViewModel() {

    private val _groups = MutableLiveData<NetworkResult<GroupResponseModel>>()
    val groups: LiveData<NetworkResult<GroupResponseModel>> = _groups


    private val _user = MutableLiveData<NetworkResult<UserResponseModel>>()
    val user: LiveData<NetworkResult<UserResponseModel>> = _user

    init {
        getUser(sharedPrefManager.getUId())
        getGroupsByUserUid(sharedPrefManager.getUId())
    }
    private fun getUser(userUid: String) {
        viewModelScope.launch {
            _user.postValue(NetworkResult.Loading)
            firestoreRepository.getUser(userUid).addOnSuccessListener { document ->
                Log.d("Deneme", document.data?.get("fullName").toString())
                _user.postValue(
                    NetworkResult.Success(
                        UserResponseModel(
                            fullName = document.data?.get(
                                "fullName"
                            ).toString(), email = document.data?.get("email").toString()
                        )
                    )
                )
            }.addOnFailureListener {

            }
        }
    }

    fun createGroup(createGroupRequestModel: CreateGroupRequestModel) {
        viewModelScope.launch {
            _groups.postValue(NetworkResult.Loading)
            val result = firestoreRepository.createGroup(createGroupRequestModel)
            result.addOnSuccessListener {
                _groups.postValue(
                    NetworkResult.Success(
                        GroupResponseModel(
                            groupUid = it.id
                        )
                    )
                )
            }.addOnFailureListener {
                NetworkResult.Error(
                    ServerErrorModel(
                        it.localizedMessage ?: "An error occurred"
                    )
                )
            }
        }
    }

    private fun getGroupsByUserUid(userUid: String) {
        viewModelScope.launch {
            _groups.postValue(NetworkResult.Loading)
            val result = firestoreRepository.getGroupsByUserUid(userUid)
            result.addOnSuccessListener {

                for (document in it) {
                    if (document.data.containsValue(userUid)) {
                        Log.d("Deneme", document.id)
                        _groups.postValue(
                            NetworkResult.Success(
                                GroupResponseModel(
                                    groupUid = document.id
                                )
                            )
                        )
                    }
                }

            }.addOnFailureListener {
                NetworkResult.Error(
                    ServerErrorModel(
                        it.localizedMessage ?: "An error occurred"
                    )
                )
            }
        }
    }

}