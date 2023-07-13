package com.evirgenoguz.spendtogether.ui.feature.expense

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evirgenoguz.spendtogether.R
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentExpenseBinding
import com.evirgenoguz.spendtogether.ext.observeLiveData
import com.evirgenoguz.spendtogether.ext.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseFragment : BaseFragment<FragmentExpenseBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentExpenseBinding
        get() = FragmentExpenseBinding::inflate

    private val viewModel by viewModels<ExpenseViewModel>()

    private lateinit var expenseAdapter: ExpenseAdapter
    override fun setupUi() {
        initListeners()
        prepareAdapter()
        arguments?.getString("groupUid")?.let { viewModel.getExpenseByGroupUid(it) }
        observeExpenseLiveData()
    }

    private fun observeExpenseLiveData() {
        observeLiveData(viewModel.expenseList) {
            it.onLoading {
                //Todo loading animation
                toast("Loading")
            }
            it.onSuccess {
                expenseAdapter.setExpenseList(it)
            }
            it.onError { toast(it.message) }
        }
    }

    private fun prepareAdapter() {
        expenseAdapter = ExpenseAdapter()
        binding.recyclerViewExpense.adapter = expenseAdapter
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