package com.evirgenoguz.spendtogether.data.service

import com.evirgenoguz.spendtogether.data.model.request.CreateGroupRequestModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

interface FirestoreService {

    fun getUser(userUid: String): Task<DocumentSnapshot>
    fun getGroupsByUserUid(userUid: String): Task<DocumentSnapshot>
    fun createGroup(createGroupRequestModel: CreateGroupRequestModel): Task<DocumentReference>

    fun deleteGroup(groupUid: String)

    fun joinGroup(groupUid: String)

}