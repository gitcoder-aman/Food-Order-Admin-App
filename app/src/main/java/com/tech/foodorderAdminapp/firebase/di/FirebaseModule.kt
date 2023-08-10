package com.tech.foodorderAdminapp.firebase.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//dependency provide of Firebase here
@Module         //every where use in project
@InstallIn(SingletonComponent::class)   //this is define scope in whole project
object FirebaseModule {

    @Provides
    @Singleton
    fun providesRealtimeDb():DatabaseReference = Firebase.database.reference.child("add_items")
}