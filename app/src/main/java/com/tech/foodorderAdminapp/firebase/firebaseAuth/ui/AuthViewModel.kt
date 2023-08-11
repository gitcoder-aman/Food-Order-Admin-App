package com.tech.foodorderAdminapp.firebase.firebaseAuth.ui

import androidx.lifecycle.ViewModel
import com.tech.foodorderAdminapp.firebase.firebaseAuth.AuthUserModel
import com.tech.foodorderAdminapp.firebase.firebaseAuth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel(){

    fun createUser(authUserModel: AuthUserModel)  = repo.createUser(authUserModel)

    fun loginUser(authUserModel: AuthUserModel) = repo.loginUser(authUserModel)

    fun logOut() = repo.logout()
}