package com.evirgenoguz.spendtogether.ui.feature.profile

import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.local.SharedPrefManager
import com.evirgenoguz.spendtogether.data.repository.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val sharedPrefManager: SharedPrefManager
) : BaseViewModel() {


    fun logout(){
        sharedPrefManager.setUId("")
    }

}