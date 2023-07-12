package com.evirgenoguz.spendtogether.ui.feature.profile

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentProfileBinding
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
    }




}