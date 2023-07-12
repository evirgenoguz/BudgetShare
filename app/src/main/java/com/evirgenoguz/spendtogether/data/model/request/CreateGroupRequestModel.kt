package com.evirgenoguz.spendtogether.data.model.request

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

data class CreateGroupRequestModel(
    val groupName: String,
    val groupOwnerUid: String,
    val memberList: List<String>? = mutableListOf(groupOwnerUid)
)
