package com.tech.foodorderAdminapp.firebase.firebaseAuth.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.tech.foodorderAdminapp.firebase.firebaseAuth.AuthUserModel
import com.tech.foodorderAdminapp.firebase.firebaseAuth.googleSignIn.SignInResult
import com.tech.foodorderAdminapp.firebase.firebaseAuth.googleSignIn.SignInState
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override fun createUser(authUserModel: AuthUserModel): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            auth.createUserWithEmailAndPassword(authUserModel.email, authUserModel.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResultState.Success("User Create Successfully"))
                        Log.d("main", "createUser: ${auth.currentUser?.uid}")
                    }
                }.addOnFailureListener {
                    trySend(ResultState.Failure(it))
                }
            awaitClose {
                close()
            }
        }

    override fun loginUser(authUserModel: AuthUserModel): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        auth.signInWithEmailAndPassword(authUserModel.email, authUserModel.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("@main", authUserModel.email)
                    Log.d("@main", authUserModel.password)
                    trySend(ResultState.Success("Login Successfully"))
                    Log.d("@main", "login: ${auth.currentUser?.uid}")
                }
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }

    override fun logout(): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)

        auth.signOut()

        trySend(ResultState.Success("Sign Out"))

        awaitClose {
            close()
        }
    }
}