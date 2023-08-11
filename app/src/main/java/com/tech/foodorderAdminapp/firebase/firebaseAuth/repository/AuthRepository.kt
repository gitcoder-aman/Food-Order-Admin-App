package com.tech.foodorderAdminapp.firebase.firebaseAuth.repository

import com.tech.foodorderAdminapp.firebase.firebaseAuth.AuthUserModel
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUser(authUserModel:AuthUserModel):Flow<ResultState<String>>

    fun loginUser(authUserModel: AuthUserModel):Flow<ResultState<String>>
    fun logout():Flow<ResultState<String>>
}