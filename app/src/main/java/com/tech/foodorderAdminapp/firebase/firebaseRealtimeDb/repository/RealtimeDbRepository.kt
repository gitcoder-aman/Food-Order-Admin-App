package com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.repository

import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.StorageReference
import com.tech.foodorderAdminapp.firebase.firebaseAuth.AuthUserModel
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.model.RealtimeModelResponse
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import com.tech.foodorderAdminapp.util.Variables
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.io.ByteArrayOutputStream
import javax.inject.Inject


class RealtimeDbRepository @Inject constructor(
    private val db: DatabaseReference,
    private val storageReference: StorageReference
) : RealtimeRepository {

    override fun insert(
        item: RealtimeModelResponse.RealtimeItems,
        bitmap: Bitmap
    ): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            if (Variables.bitmap != null) {
                val imageRef = storageReference.child(Variables.bitmap.toString())
                val baos = ByteArrayOutputStream()
                Variables.bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val imageData = baos.toByteArray()

                imageRef.putBytes(imageData).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        imageRef.downloadUrl.addOnSuccessListener { uri ->
                            item.itemImage = uri.toString()

                            db.child("add_items").push()
                                .setValue(item) //push data with unique id in firebase database
                                .addOnCompleteListener {
                                    if (it.isSuccessful)
                                        trySend(ResultState.Success("Data inserted Successfully.."))
                                }.addOnFailureListener {
                                    trySend(ResultState.Failure(it))
                                }

                        }
                        Log.d("insertTAG", "insert: image uploaded")
                    }

                }.addOnFailureListener {
                    Log.d("insertTAG", "insert: {$it}")
                }
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
        db.child("add_items").addValueEventListener(valueEvent)
        awaitClose {
            db.child("add_items").removeEventListener(valueEvent)  //callback detached
            close()
        }
    }

    override fun delete(key: String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        db.child("add_items").child(key).removeValue()
            .addOnCompleteListener {
                trySend(ResultState.Success("item deleted"))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun update(realtimeModel: RealtimeModelResponse): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            val map = HashMap<String, Any>()
            map["itemName"] = realtimeModel.items?.itemName!!
            map["price"] = realtimeModel.items.price
            map["description"] = realtimeModel.items.description
            map["itemIngredients"] = realtimeModel.items.itemIngredients
            map["itemImage"] = realtimeModel.items.itemImage

            db.child(realtimeModel.key!!).updateChildren(map).addOnCompleteListener {
                trySend(ResultState.Success("item update Successfully"))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }

            awaitClose {
                close()
            }
        }

    override fun fetchUserData(uid: String) : Flow<ResultState<AuthUserModel>> = callbackFlow{
        trySend(ResultState.Loading)

        db.child("user_details").child(uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val authUserModel = snapshot.getValue(AuthUserModel::class.java)!!
                        trySend(ResultState.Success(authUserModel))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("fetchData", "onCancelled: {${error.message}}")
                }
            })

        awaitClose {
            close()
        }
    }

    override fun userDataUpdate(authUserModel: AuthUserModel): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        val map = HashMap<String, Any>()
        map["ownerAddress"] = authUserModel.ownerAddress
        map["ownerName"] = authUserModel.ownerName
        map["ownerPhone"] = authUserModel.ownerPhone
        map["password"] = authUserModel.password
        map["restaurantName"] = authUserModel.restaurantName
        Log.d("userUpdate", "userDataUpdate: {${authUserModel.uid}}")
        db.child("user_details").child(authUserModel.uid).updateChildren(map).addOnCompleteListener {
            trySend(ResultState.Success("Profile update Successfully"))
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }

        awaitClose {
            close()
        }

    }
}