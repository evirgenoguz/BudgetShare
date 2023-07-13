package com.evirgenoguz.spendtogether.ui


import android.view.LayoutInflater
import androidx.navigation.NavController
import com.evirgenoguz.spendtogether.core.BaseActivity
import com.evirgenoguz.spendtogether.databinding.ActivityMainBinding
import com.evirgenoguz.spendtogether.ext.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupUI() {
        //that comes from activity extension
        navController = findNavController()
    }

}