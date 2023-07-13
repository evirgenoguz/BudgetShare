package com.evirgenoguz.spendtogether.data.repository

import com.evirgenoguz.spendtogether.data.mock.Expense
import com.evirgenoguz.spendtogether.data.service.TransactionService

/**
 * @Author: Oguz Evirgen
 * @Date: 13.07.2023
 */

class TransactionRepository : TransactionService {

    val expenses: MutableList<Expense> = mutableListOf(
        Expense(
            100,
            "f5zKj8ycoUgnbOPddbSIsdHDBf22",
            "mYnbxAVSbQw3IcCbryvk",
            "Market"
        ),
        Expense(
            80,
            "f5zKj8ycoUgnbOPddbSIsdHDBf22",
            "mYnbxAVSbQw3IcCbryvk",
            "Pazar"
        ),
        Expense(
            20,
            "f5zKj8ycoUgnbOPddbSIsdHDBf22",
            "mYnbxAVSbQw3IcCbryvk",
            "Ivir Zivir"
        ),
        Expense(
            250,
            "f5zKj8ycoUgnbOPddbSIsdHDBf22",
            "mYnbxAVSbQw3IcCbryvk",
            "Teknoloji"
        ),
        Expense(
            100,
            "0IZirRc7OdW2kjvYrRyaSZ8Y3up2",
            "mYnbxAVSbQw3IcCbryvk",
            "Market"
        ),
        Expense(
            100,
            "0IZirRc7OdW2kjvYrRyaSZ8Y3up2",
            "mYnbxAVSbQw3IcCbryvk",
            "Market"
        ),
        Expense(
            100,
            "0IZirRc7OdW2kjvYrRyaSZ8Y3up2",
            "mYnbxAVSbQw3IcCbryvk",
            "Market"
        )

    )
    override fun getExpenseByGroupUid(groupUid: String): MutableList<Expense> {
        val returnList = mutableListOf<Expense>()

        expenses.forEach {
            if (it.expenseGroupUid == groupUid) {
                returnList.add(it)
            }
        }
        return returnList
    }

}