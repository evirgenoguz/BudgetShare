package com.evirgenoguz.spendtogether.ui.feature.login

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.data.NetworkResult
import com.evirgenoguz.spendtogether.data.model.request.LoginRequestModel
import com.evirgenoguz.spendtogether.databinding.FragmentLoginBinding
import com.evirgenoguz.spendtogether.ext.observeLiveData
import com.evirgenoguz.spendtogether.ext.safeNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val bindingInflater: (inflater: LayoutInflater) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private val viewModel by viewModels<LoginViewModel>()
    override fun setupUi() {
        initListeners()
        observeLoginLiveData()
    }

    private fun initListeners() {
        binding.apply {
            buttonLogin.setOnClickListener {
                login()
            }
            tvRegister.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }

    private fun login() {
        binding.apply {
            val email = tietEmail.text.toString().trim()
            val password = tietPassword.text.toString().trim()

            viewModel.login(LoginRequestModel(email, password))
        }
    }


    private fun observeLoginLiveData() {
        lifecycleScope.launchWhenStarted {
            observeLiveData(viewModel.login) {
                when (it) {
                    is NetworkResult.Loading -> {
                        //Todo add dialog for loading
                        Log.d(TAG, "Loading")
                    }

                    is NetworkResult.Success -> {
                        if (binding.checkBoxKeepMeLoggedIn.isChecked) {
                            viewModel.saveUserUidToSharedPref(it.body.uid)
                        }
                        findNavController().safeNavigation(LoginFragmentDirections.actionLoginFragmentToGroupListFragment())
                        Log.d(TAG, "Succesfully Login : ${it.body.uid}")
                    }

                    is NetworkResult.Error -> {
                        Log.e("Login Fragment", it.error.message)
                    }
                }
            }
        }
    }


}