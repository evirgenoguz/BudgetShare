package com.evirgenoguz.spendtogether.ui.feature.group_list

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evirgenoguz.spendtogether.R
import com.evirgenoguz.spendtogether.core.BaseFragment
import com.evirgenoguz.spendtogether.databinding.FragmentGroupListBinding
import com.evirgenoguz.spendtogether.ext.observeLiveData
import com.evirgenoguz.spendtogether.ext.toast
import com.evirgenoguz.spendtogether.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupListFragment : BaseFragment<FragmentGroupListBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentGroupListBinding
        get() = FragmentGroupListBinding::inflate

    private val viewModel by viewModels<GroupListViewModel>()
    private val sharedViewModel by activityViewModels<MainViewModel>()

    private lateinit var groupListAdapter: GroupListAdapter
    override fun setupUi() {
        initListeners()
        prepareAdapter()

        observeEvents()
        observeGroupListLiveData()
    }

    private fun prepareAdapter() {
        groupListAdapter = GroupListAdapter()
        binding.recyclerViewGroup.apply {
            adapter = groupListAdapter
        }
    }

    private fun observeGroupListLiveData() {
        observeLiveData(viewModel.groupList) {
            it.onLoading {
                //Todo loading animation
                toast("Loading")
            }
            it.onSuccess {
                groupListAdapter.setGroupList(it)
            }
            it.onError { toast(it.message) }
        }
    }

    private fun observeEvents() {
        observeLiveData(viewModel.user) {
            it.onSuccess {
                sharedViewModel.setCurrentUser(it)
            }.onError {
                toast(it.message)
            }.onLoading {

            }
        }
    }

    private fun initListeners() {
        binding.toolBar.apply {
            inflateMenu(R.menu.menu_group_list)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_profile -> {
                        findNavController().navigate(GroupListFragmentDirections.actionGroupListFragmentToProfileFragment())
                        true
                    }

                    else -> false
                }
            }
            setTitle(R.string.app_name)
        }
        binding.fabAddGroup.setOnClickListener {
            findNavController().navigate(GroupListFragmentDirections.actionGroupListFragmentToCreateGroupFragment())
        }
    }


}