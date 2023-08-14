package com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.repository

import android.graphics.Bitmap
import com.tech.foodorderAdminapp.firebase.firebaseAuth.AuthUserModel
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.model.RealtimeModelResponse
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RealtimeRepository {

    fun insert(
        item: RealtimeModelResponse.RealtimeItems, bitmap: Bitmap
    ): Flow<ResultState<String>>    //return string after successful insert the data

//    fun uploadImageInStorage(bitmap : Bitmap) : Flow<ResultState<String>>
    fun getItems(): Flow<ResultState<List<RealtimeModelResponse>>>

    fun delete(
        key: String,
    ): Flow<ResultState<String>>

    fun update(
        realtimeModel: RealtimeModelResponse
    ): Flow<ResultState<String>>

    fun fetchUserData(uid : String) : Flow<ResultState<AuthUserModel>>
    fun userDataUpdate(authUserModel : AuthUserModel) : Flow<ResultState<String>>
}