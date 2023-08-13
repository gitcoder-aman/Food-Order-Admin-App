package com.tech.foodorderAdminapp.firebase.firebaseAuth.googleSignIn

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
)
