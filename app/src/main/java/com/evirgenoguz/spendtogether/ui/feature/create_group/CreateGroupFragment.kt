package com.evirgenoguz.spendtogether.ui.feature.create_group

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.data.model.request.CreateGroupRequestModel
import com.evirgenoguz.spendtogether.databinding.FragmentCreateGroupBinding
import com.evirgenoguz.spendtogether.ui.feature.group_list.GroupListViewModel


class CreateGroupFragment : BaseFragment<FragmentCreateGroupBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentCreateGroupBinding
        get() = FragmentCreateGroupBinding::inflate

    private val viewModel by viewModels<CreateGroupViewModel>()
    private val viewModelGroupList by viewModels<GroupListViewModel>()


    override fun setupUi() {
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            buttonLogin.setOnClickListener {
                viewModelGroupList.createGroup(
                    CreateGroupRequestModel(
                        tietGroupName.text.toString().trim(),
                        viewModel.getUserUidFromSharedPref())
                )
            }
        }
    }

}