package com.evirgenoguz.spendtogether.ui.feature.expense

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import com.evirgenoguz.spendtogether.R
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentExpenseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseFragment : BaseFragment<FragmentExpenseBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentExpenseBinding
        get() = FragmentExpenseBinding::inflate

    override fun setupUi() {
        initListeners()
    }

    private fun initListeners() {
        binding.toolBar.apply {
            inflateMenu(R.menu.menu_expense_list)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_balance -> {
                        findNavController().navigate(ExpenseFragmentDirections.actionExpenseFragmentToBalanceFragment())
                        true
                    }

                    else -> false
                }
            }
            title = context.getString(R.string.expenses)
        }
    }


}