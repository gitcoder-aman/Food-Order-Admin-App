package com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.repository

import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.RealtimeModelResponse
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RealtimeRepository {

    fun insert(
        item: RealtimeModelResponse.RealtimeItems
    ): Flow<ResultState<String>>    //return string after successful insert the data

    fun getItems(): Flow<ResultState<List<RealtimeModelResponse>>>

    fun delete(
        key: String,
    ): Flow<ResultState<String>>

    fun update(
        realtimeModel: RealtimeModelResponse
    ): Flow<ResultState<String>>
}