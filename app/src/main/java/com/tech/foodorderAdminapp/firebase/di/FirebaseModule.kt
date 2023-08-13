package com.tech.foodorderAdminapp.firebase.di

import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

//dependency provide of Firebase here
@Module
@InstallIn(SingletonComponent::class)   //this is define scope in whole project //every where use in project
object FirebaseModule {

    @Provides
    @Singleton
    fun providesRealtimeDb():DatabaseReference = Firebase.database.reference.child("add_items")

    @Singleton
    @Provides
    fun providesFirebaseAuth():FirebaseAuth = Firebase.auth

}