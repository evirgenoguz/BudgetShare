package com.evirgenoguz.spendtogether.data.repository

import com.evirgenoguz.spendtogether.data.model.request.CreateGroupRequestModel
import com.evirgenoguz.spendtogether.data.service.FirestoreService
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

/**
 * @Author: Oguz Evirgen
 * @Date: 12.07.2023
 */

class FirestoreRepository @Inject constructor(
    private val fireStore: FirebaseFirestore
) : FirestoreService {

    override fun getUser(userUid: String): Task<DocumentSnapshot> {
        return fireStore.collection(USER_COLLECTION).document(userUid).get()
    }

    override fun getUserGroups(userUid: String): Task<DocumentSnapshot> {
        return fireStore.collection(USER_COLLECTION).document(userUid).get()
    }

    override fun getGroupByGroupUid(groupUid: String): Task<DocumentSnapshot> {
        return fireStore.collection(COLLECTION_GROUP).document(groupUid).get()
    }



    override fun createGroup(createGroupRequestModel: CreateGroupRequestModel): Task<DocumentReference> {
        var groupUid: String

        val result = fireStore.collection(COLLECTION_GROUP)
            .add(createGroupRequestModel).addOnCompleteListener {
                groupUid = it.result.id
                fireStore.collection(USER_COLLECTION)
                    .document(createGroupRequestModel.groupOwnerUid)
                    .update(
                        mapOf(
                            "groups" to FieldValue.arrayUnion(groupUid)
                        )
                    )
            }

        return result
    }

    override fun deleteGroup(groupUid: String) {
    }

    override fun joinGroup(groupUid: String) {

    }

    companion object {
        const val COLLECTION_GROUP = "group"
        const val USER_COLLECTION = "user"
    }

}