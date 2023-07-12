package com.evirgenoguz.spendtogether.ui.feature.start

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentStartBinding
        get() = FragmentStartBinding::inflate

    private val viewModel by viewModels<StartViewModel>()

    override fun setupUi() {
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            buttonLogin.setOnClickListener {
                findNavController().navigate(StartFragmentDirections.actionStartFragmentToLoginFragment())
            }
            tvRegister.setOnClickListener {
                findNavController().navigate(StartFragmentDirections.actionStartFragmentToRegisterFragment())
            }
        }
    }


}