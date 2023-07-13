package com.evirgenoguz.spendtogether.ui.feature.expense

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evirgenoguz.spendtogether.R
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentExpenseBinding
import com.evirgenoguz.spendtogether.ext.observeLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseFragment : BaseFragment<FragmentExpenseBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentExpenseBinding
        get() = FragmentExpenseBinding::inflate

    private val viewModel by viewModels<ExpenseViewModel>()

    private lateinit var expenseAdapter: ExpenseAdapter

    private var userExpense = 0
    private var expenseSum = 0
    private val distinctUser = mutableSetOf<String>()


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
                Log.d(TAG, "Loading")
            }
            it.onSuccess {
                it.forEach{
                    expenseSum += it.amount
                    if (viewModel.userUid == it.expenseOwnerUid){
                        userExpense += it.amount
                    }
                    expenseSum += it.amount
                    distinctUser.add(it.expenseOwnerUid)
                }
                expenseAdapter.setExpenseList(it)
            }
            it.onError { Log.d(TAG, it.message) }
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
                        val argumentValue = (userExpense - (expenseSum / distinctUser.size))
                        findNavController().navigate(ExpenseFragmentDirections.actionExpenseFragmentToBalanceFragment(argumentValue))
                        true
                    }

                    else -> false
                }
            }
            title = context.getString(R.string.expenses)
        }

        binding.fabAddExpense.setOnClickListener {
            findNavController().navigate(ExpenseFragmentDirections.actionExpenseFragmentToCreateExpenseFragment())
        }
    }


}