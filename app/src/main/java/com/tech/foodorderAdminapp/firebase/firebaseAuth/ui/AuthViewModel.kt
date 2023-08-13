package com.tech.foodorderAdminapp.firebase.firebaseAuth.ui

import androidx.lifecycle.ViewModel
import com.tech.foodorderAdminapp.firebase.firebaseAuth.AuthUserModel
import com.tech.foodorderAdminapp.firebase.firebaseAuth.googleSignIn.SignInResult
import com.tech.foodorderAdminapp.firebase.firebaseAuth.googleSignIn.SignInState
import com.tech.foodorderAdminapp.firebase.firebaseAuth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel(){

    fun createUser(authUserModel: AuthUserModel)  = repo.createUser(authUserModel)

    fun loginUser(authUserModel: AuthUserModel) = repo.loginUser(authUserModel)

    fun logOut() = repo.logout()

    //for google signIn
    val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update {
            SignInState()
        }
    }
}