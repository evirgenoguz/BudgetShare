package com.evirgenoguz.spendtogether.data.service

import com.evirgenoguz.spendtogether.data.model.request.CreateGroupRequestModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

interface FirestoreService {

    fun getGroupsByUserUid(userUid: String): Task<QuerySnapshot>
    fun createGroup(createGroupRequestModel: CreateGroupRequestModel): Task<DocumentReference>

    fun deleteGroup(groupUid: String)

    fun joinGroup(groupUid: String)

}