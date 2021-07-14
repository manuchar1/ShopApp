package com.tbcacademy.shopapp.di

import com.tbcacademy.shopapp.repositories.auth.AuthRepository
import com.tbcacademy.shopapp.repositories.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
object AuthModule {

    @ActivityScoped
    @Provides
    fun provideAuthRepository() = AuthRepositoryImpl() as AuthRepository
}