package com.evirgenoguz.spendtogether.ui.feature.expense

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evirgenoguz.spendtogether.data.mock.Expense
import com.evirgenoguz.spendtogether.databinding.ItemExpenseBinding

/**
 * @Author: Oguz Evirgen
 * @Date: 13.07.2023
 */

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseListViewHolder>() {
    private var expenseList = listOf<Expense>()

    fun setExpenseList(expenseList: List<Expense>?) {
        this.expenseList = expenseList ?: emptyList()
        notifyDataSetChanged()
    }

    inner class ExpenseListViewHolder(
        private val binding: ItemExpenseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(expense: Expense) {
            binding.textViewPaidBy.text = buildString {
                append("Paid by ")
                append(expense.expenseOwnerFullName)
            }
            binding.textViewExpenseDescription.text = expense.description
            binding.textViewAmount.text = expense.amount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseListViewHolder {
        return ExpenseListViewHolder(
            ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = expenseList.size

    override fun onBindViewHolder(holder: ExpenseListViewHolder, position: Int) {
        val expense = expenseList[position]
        holder.bind(expense)
    }
}