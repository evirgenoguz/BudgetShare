package com.evirgenoguz.spendtogether.ui.feature.group_list

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.evirgenoguz.spendtogether.R
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.data.model.request.CreateGroupRequestModel
import com.evirgenoguz.spendtogether.databinding.FragmentGroupListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupListFragment : BaseFragment<FragmentGroupListBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentGroupListBinding
        get() = FragmentGroupListBinding::inflate

    private val viewModel by viewModels<GroupListViewModel>()
    override fun setupUi() {
        initListeners()
    }

    private fun initListeners() {
        binding.toolBar.inflateMenu(R.menu.menu_group_list)
        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_profile -> {
                    //Todo navigate profile screen
                    true
                }
                else -> false
            }
        }
        binding.toolBar.setTitle(R.string.app_name)
        binding.toolBar.setNavigationIcon(R.drawable.ic_back)

        binding.toolBar.setNavigationOnClickListener {
            //Todo navigate navigateUp
        }

        binding.fabAddGroup.setOnClickListener {
            viewModel.createGroup(CreateGroupRequestModel("test", "94hEp6GFRQTKPLCCSY4QbUTpxAx2"))
        }
    }


}