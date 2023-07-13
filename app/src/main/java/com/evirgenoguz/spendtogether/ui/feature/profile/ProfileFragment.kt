package com.evirgenoguz.spendtogether.ui.feature.profile

import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentProfileBinding
import com.evirgenoguz.spendtogether.ext.observeLiveData
import com.evirgenoguz.spendtogether.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    private val viewModel by viewModels<ProfileViewModel>()
    private val sharedViewModel by activityViewModels<MainViewModel>()

    override fun setupUi() {
        sharedViewModel.currentUser?.let {
            binding.textViewEmail.text = it.email
            binding.textViewUserName.text = it.fullName
        }

        initListeners()
        observeCurrencyLiveData()

    }

    private fun observeCurrencyLiveData() {
        observeLiveData(viewModel.currency) {
            it.onLoading {
                //Todo add dialog for loading
                Log.d(TAG, "Loading")
            }
            it.onSuccess {
                binding.apply {
                    textViewDolar.text = "Dolar: ${it.ourData.TRY}"
                    textViewEuro.text = "Euro: ${it.ourData.TRY / it.ourData.EUR}"
                    textViewSterlin.text = "Sterlin: ${it.ourData.TRY / it.ourData.GBP }"
                }
            }
            it.onError {
                Log.d("Profile Fragment", it.message)
            }
        }
    }

    private fun initListeners() {
        binding.apply {
            buttonLogout.setOnClickListener {
                viewModel.logout()
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToStartFragment())

            }
        }
    }


}