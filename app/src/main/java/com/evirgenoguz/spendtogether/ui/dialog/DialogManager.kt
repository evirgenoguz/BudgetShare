package com.evirgenoguz.spendtogether.ui.dialog


import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ProgressBar

/**
 * @Author: Oguz Evirgen
 * @Date: 13.07.2023
 */

class DialogManager(private val context: Context) {
    private var loadingDialog: Dialog? = null

    fun showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = Dialog(context)
            loadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            loadingDialog?.setContentView(ProgressBar(context))
            loadingDialog?.setCancelable(false)
        }

        loadingDialog?.show()
    }

    fun hideLoadingDialog() {
        loadingDialog?.dismiss()
    }
}