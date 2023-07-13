package com.evirgenoguz.spendtogether.ui.feature.group_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evirgenoguz.spendtogether.data.model.response.GroupResponseModel
import com.evirgenoguz.spendtogether.databinding.ItemGroupBinding

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

class GroupListAdapter : RecyclerView.Adapter<GroupListAdapter.GroupListViewHolder>() {

    var onItemClick: ((GroupResponseModel) -> Unit)? = null
    private var groupList = listOf<GroupResponseModel>()

    fun setGroupList(groupList: List<GroupResponseModel>?) {
        this.groupList = groupList ?: emptyList()
        notifyDataSetChanged()
    }

    inner class GroupListViewHolder(
        private val binding: ItemGroupBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(group: GroupResponseModel) {
            binding.textViewGroupName.text = group.groupName

            itemView.setOnClickListener {
                onItemClick!!.invoke(group)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupListViewHolder {
        return GroupListViewHolder(
            ItemGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = groupList.size

    override fun onBindViewHolder(holder: GroupListViewHolder, position: Int) {
        val group = groupList[position]

        holder.bind(group)
    }
}