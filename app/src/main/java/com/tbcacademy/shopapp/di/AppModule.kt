package com.tbcacademy.shopapp.di

import android.content.Context
import com.tbcacademy.shopapp.network.PostService
import com.tbcacademy.shopapp.repositories.posts.PostRepository
import com.tbcacademy.shopapp.repositories.posts.PostRepositoryImpl
import com.tbcacademy.shopapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi():PostService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PostService::class.java)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    fun provideMainDispather() = Dispatchers.Main as CoroutineDispatcher

    @Singleton
    @Provides
    fun providePostRepository(
        apiService:PostService
    ):PostRepository = PostRepositoryImpl(apiService)



}