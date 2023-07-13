package com.evirgenoguz.spendtogether.ui.feature.create_expense

import android.view.LayoutInflater
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentCreateExpenseBinding

class CreateExpenseFragment : BaseFragment<FragmentCreateExpenseBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCreateExpenseBinding
        get() = FragmentCreateExpenseBinding::inflate

    override fun setupUi() {

    }


}