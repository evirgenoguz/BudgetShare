package com.evirgenoguz.spendtogether.data.service

import com.evirgenoguz.spendtogether.data.mock.Expense

/**
 * @Author: Oguz Evirgen
 * @Date: 13.07.2023
 */

interface TransactionService {

    fun getExpenseByGroupUid(groupUid: String): MutableList<Expense>
}