package com.evirgenoguz.spendtogether.data.repository

import com.evirgenoguz.spendtogether.data.model.request.CreateGroupRequestModel
import com.evirgenoguz.spendtogether.data.service.FirestoreService
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

class FirestoreRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
) : FirestoreService {
    override fun getGroupsByUserUid(userUid: String): Task<QuerySnapshot> {
        val query = fireStore.collection(COLLECTION_GROUP)
            .whereArrayContains("memberList", userUid)

        return query.get()
    }

    override fun createGroup(createGroupRequestModel: CreateGroupRequestModel): Task<DocumentReference> {
        return fireStore.collection(COLLECTION_GROUP)
            .add(createGroupRequestModel)
    }

    override fun deleteGroup(groupUid: String) {
    }

    override fun joinGroup(groupUid: String) {

    }

    companion object {
        const val COLLECTION_GROUP = "group"
    }

}