package com.evirgenoguz.spendtogether.ui.feature.login

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val bindingInflater: (inflater: LayoutInflater) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun setupUi() {
        TODO("Not yet implemented")
    }

    private val viewModel by viewModels<LoginViewModel>()



}