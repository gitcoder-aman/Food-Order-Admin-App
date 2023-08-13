package com.tech.foodorderAdminapp.firebase.firebaseAuth.repository

import com.tech.foodorderAdminapp.firebase.firebaseAuth.AuthUserModel
import com.tech.foodorderAdminapp.firebase.firebaseAuth.googleSignIn.SignInState
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {

    fun createUser(authUserModel:AuthUserModel):Flow<ResultState<String>>

    fun loginUser(authUserModel: AuthUserModel):Flow<ResultState<String>>

    fun logout():Flow<ResultState<String>>

}