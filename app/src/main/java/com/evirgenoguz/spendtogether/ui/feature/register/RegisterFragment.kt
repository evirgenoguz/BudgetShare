package com.evirgenoguz.spendtogether.ui.feature.register

import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.model.request.RegisterRequestModel
import com.evirgenoguz.spendtogether.databinding.FragmentRegisterBinding
import com.evirgenoguz.spendtogether.ext.observeLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override val bindingInflater: (inflater: LayoutInflater) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    private val viewModel by viewModels<RegisterViewModel>()

    override fun setupUi() {
        initListeners()
        observerRegisterLiveData()
    }

    private fun observerRegisterLiveData() {
        lifecycleScope.launchWhenStarted {
            observeLiveData(viewModel.register) {
                when (it) {
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        Toast.makeText(
                            context,
                            //TODO: get uid and navigate to inside app
                            "${it.body.uid} successfully auth",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is NetworkResult.Error -> {
                        Log.e("Register Fragment", it.error.message)
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            buttonRegister.setOnClickListener {
                register()
            }
            tvLogin.setOnClickListener {
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
        }
    }

    private fun register() {
        binding.apply {
            val registerRequestModel = RegisterRequestModel(
                tietEmail.text.toString().trim(),
                tietPassword.text.toString().trim()
            )
        }
    }


}