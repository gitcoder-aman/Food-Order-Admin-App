package com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.RealtimeModelResponse
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class RealtimeDbRepository @Inject constructor(
    private val db: DatabaseReference
) : RealtimeRepository {
    override fun insert(item: RealtimeModelResponse.RealtimeItems): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            db.push().setValue(item) //push data with unique id
                .addOnCompleteListener {
                    if (it.isSuccessful)
                        trySend(ResultState.Success("Data inserted Successfully.."))
                }.addOnFailureListener {
                    trySend(ResultState.Failure(it))
                }
            awaitClose { //detached here when app is fully closed
                close()
            }
        }

    override fun getItems(): Flow<ResultState<List<RealtimeModelResponse>>> = callbackFlow {
        trySend(ResultState.Loading)

        val valueEvent = object : ValueEventListener {  //for getting all data from firebase
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val items = snapshot.children.map {
                        RealtimeModelResponse(
                            it.getValue(RealtimeModelResponse.RealtimeItems::class.java),
                            key = it.key
                        )
                    }
                    trySend(ResultState.Success(items))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Failure(error.toException()))
            }

        }
        db.addValueEventListener(valueEvent)
        awaitClose {
            db.removeEventListener(valueEvent)  //callback detached
            close()
        }
    }

    override fun delete(key: String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        db.child(key).removeValue()
            .addOnCompleteListener {
                trySend(ResultState.Success("item deleted"))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose{
            close()
        }
    }

    override fun update(realtimeModel: RealtimeModelResponse): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        val map = HashMap<String,Any>()
        map["itemName"] = realtimeModel.items?.itemName!!
        map["price"] = realtimeModel.items.price

        db.child(realtimeModel.key!!).updateChildren(map).addOnCompleteListener {
            trySend(ResultState.Success("item update Successfully"))
        }.addOnFailureListener{
            trySend(ResultState.Failure(it))
        }

        awaitClose {
            close()
        }
    }
}