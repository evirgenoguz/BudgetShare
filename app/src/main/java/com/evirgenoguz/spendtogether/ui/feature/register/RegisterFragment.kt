package com.evirgenoguz.spendtogether.ui.feature.register

import android.util.Log
import android.view.LayoutInflater
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
                        //Todo loading dialog
                        Log.d(TAG, "Loading")
                    }

                    is NetworkResult.Success -> {
                        if (binding.checkBoxKeepMeLoggedIn.isChecked) {
                            viewModel.saveUserUidToSharedPref(it.body.uid)
                        }
                        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToGroupListFragment())
                        Log.d(TAG, "Successfully auth: ${it.body.uid}")
                    }

                    is NetworkResult.Error -> {
                        Log.e(TAG, it.error.message)
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
                tietFullName.text.toString().trim(),
                tietEmail.text.toString().trim(),
                tietPassword.text.toString().trim()
            )
            viewModel.register(registerRequestModel)
        }
    }


}