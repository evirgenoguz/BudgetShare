package com.evirgenoguz.spendtogether.ext

import android.app.Activity
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.evirgenoguz.spendtogether.R

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.toast(@StringRes message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/**
 * USAGE
 * toast("This is toast message")
 * toast(R.string.toast_message)
 */

/**
 * It finds the NavController of FragmentContainerView object which has passed id.
 */
fun AppCompatActivity.findNavController(
    @IdRes id: Int = R.id.nav_host_fragment_content
): NavController {
    val hostFragment = supportFragmentManager.findFragmentById(id)
            as NavHostFragment
    return hostFragment.navController
}