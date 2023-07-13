package com.evirgenoguz.spendtogether.ui.feature.balance

import android.view.LayoutInflater
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentBalanceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BalanceFragment : BaseFragment<FragmentBalanceBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentBalanceBinding
        get() = FragmentBalanceBinding::inflate

    override fun setupUi() {
        initListeners()
    }

    private fun initListeners() {
        var balanceText = ""
        if ((arguments?.getInt("balance") ?: 0) > 0){
            balanceText = " +${arguments?.getInt("balance")}"
        } else {
            balanceText = arguments?.getInt("balance").toString()
        }
        binding.textViewbalance.text = balanceText
    }


}