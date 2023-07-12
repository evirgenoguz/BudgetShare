package com.evirgenoguz.spendtogether.ui.feature.start

import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.local.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) : BaseViewModel() {

    fun isUidNotEmpty() = sharedPrefManager.getUId() != ""


}