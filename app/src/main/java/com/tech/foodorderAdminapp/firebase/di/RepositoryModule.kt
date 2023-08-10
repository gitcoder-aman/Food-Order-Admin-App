package com.tech.foodorderAdminapp.firebase.di

import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.repository.RealtimeDbRepository
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.repository.RealtimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

//dependency provide of interface here

@Module
@InstallIn(ViewModelComponent::class)   //this is define scope in whole project,now here scope till viewModel
abstract class RepositoryModule {

    @Binds
    abstract fun providesRealtimeRepository(
        repo : RealtimeDbRepository
    ) : RealtimeRepository
}