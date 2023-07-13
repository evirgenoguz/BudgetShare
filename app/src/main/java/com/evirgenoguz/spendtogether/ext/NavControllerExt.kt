package com.evirgenoguz.spendtogether.ext

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

fun NavController.safeNavigation(@IdRes id: Int){
    runCatching {
        navigate(id)
    }
}

fun NavController.safeNavigation(action: NavDirections){
    runCatching {
        navigate(action)
    }
}