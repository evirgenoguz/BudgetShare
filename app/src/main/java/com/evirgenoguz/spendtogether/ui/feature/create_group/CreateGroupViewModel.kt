package com.evirgenoguz.spendtogether.ui.feature.create_group

import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.local.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author: Oguz Evirgen
 * @Date: 13.07.2023
 */

@HiltViewModel
class CreateGroupViewModel @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) : BaseViewModel() {

    fun saveUserUidToSharedPref(uid: String) {
        sharedPrefManager.setUId(uid)
    }

    fun getUserUidFromSharedPref() = sharedPrefManager.getUId()
}