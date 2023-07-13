package com.evirgenoguz.spendtogether.ui.feature.expense

import android.view.LayoutInflater
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentExpenseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseFragment : BaseFragment<FragmentExpenseBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentExpenseBinding
        get() = FragmentExpenseBinding::inflate

    override fun setupUi() {

    }


}