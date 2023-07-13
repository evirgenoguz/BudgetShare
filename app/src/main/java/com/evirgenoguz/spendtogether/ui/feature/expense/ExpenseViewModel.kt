package com.evirgenoguz.spendtogether.ui.feature.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.evirgenoguz.spendtogether.core.BaseViewModel
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.mock.Expense
import com.evirgenoguz.spendtogether.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : BaseViewModel() {

    private val _expenseList = MutableLiveData<NetworkResult<List<Expense>>>()
    val expenseList: LiveData<NetworkResult<List<Expense>>> = _expenseList



    fun getExpenseByGroupUid(groupUid: String) {
        viewModelScope.launch {
            _expenseList.postValue(NetworkResult.Loading)
            val result = transactionRepository.getExpenseByGroupUid(groupUid)
            _expenseList.postValue(NetworkResult.Success(result))
        }
    }
}