package com.evirgenoguz.spendtogether.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.evirgenoguz.spendtogether.R
import com.evirgenoguz.spendtogether.core.BaseActivity
import com.evirgenoguz.spendtogether.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupUI() {
        TODO("Not yet implemented")
    }

}