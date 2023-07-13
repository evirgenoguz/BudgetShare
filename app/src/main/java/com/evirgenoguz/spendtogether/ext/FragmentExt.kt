package com.evirgenoguz.spendtogether.ext

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.toast(@StringRes message: Int) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

/**
 * USAGE
 * toast("This is toast message")
 * toast(R.string.toast_message)
 */

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, block: (T) -> Unit){
    liveData.observe(viewLifecycleOwner){
        it?.let(block)
    }
}