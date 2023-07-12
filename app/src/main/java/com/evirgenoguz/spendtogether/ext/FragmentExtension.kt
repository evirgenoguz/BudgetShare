package com.evirgenoguz.spendtogether.ext

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

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