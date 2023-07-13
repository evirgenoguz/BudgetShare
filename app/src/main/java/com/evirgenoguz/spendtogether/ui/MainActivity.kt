package com.evirgenoguz.spendtogether.ui


import android.view.LayoutInflater
import androidx.navigation.NavController
import com.evirgenoguz.spendtogether.core.BaseActivity
import com.evirgenoguz.spendtogether.data.mock.Expense
import com.evirgenoguz.spendtogether.data.repository.TransactionRepository
import com.evirgenoguz.spendtogether.databinding.ActivityMainBinding
import com.evirgenoguz.spendtogether.ext.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupUI() {
        //that comes from activity extension
        navController = findNavController()

        val transactionRepository = TransactionRepository()
        transactionRepository.expenses.add(
            Expense(
                100,
                "f5zKj8ycoUgnbOPddbSIsdHDBf22",
                "mYnbxAVSbQw3IcCbryvk",
                "Market"
            )
        )
        transactionRepository.expenses.add(
            Expense(
                80,
                "f5zKj8ycoUgnbOPddbSIsdHDBf22",
                "mYnbxAVSbQw3IcCbryvk",
                "Pazar"
            )
        )
        transactionRepository.expenses.add(
            Expense(
                20,
                "f5zKj8ycoUgnbOPddbSIsdHDBf22",
                "mYnbxAVSbQw3IcCbryvk",
                "Ivir Zivir"
            )
        )
        transactionRepository.expenses.add(
            Expense(
                250,
                "f5zKj8ycoUgnbOPddbSIsdHDBf22",
                "mYnbxAVSbQw3IcCbryvk",
                "Teknoloji"
            )
        )

        transactionRepository.expenses.add(
            Expense(
                100,
                "0IZirRc7OdW2kjvYrRyaSZ8Y3up2",
                "mYnbxAVSbQw3IcCbryvk",
                "Market"
            )
        )

        transactionRepository.expenses.add(
            Expense(
                100,
                "0IZirRc7OdW2kjvYrRyaSZ8Y3up2",
                "mYnbxAVSbQw3IcCbryvk",
                "Market"
            )
        )

        transactionRepository.expenses.add(
            Expense(
                100,
                "0IZirRc7OdW2kjvYrRyaSZ8Y3up2",
                "mYnbxAVSbQw3IcCbryvk",
                "Market"
            )
        )
    }

}