package com.evirgenoguz.spendtogether.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

@Singleton
class SharedPrefManager @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPref = context.getSharedPreferences("myPref", Context.MODE_PRIVATE)


    fun setUId(uid: String) {
        sharedPref.edit().putString(KEY_UID, uid).apply()
    }

    fun getUId(): String {
        return sharedPref.getString(KEY_UID, "") ?: EMPTY
    }


    companion object {
        private const val KEY_UID = "UID"

        private const val EMPTY = ""
    }
}