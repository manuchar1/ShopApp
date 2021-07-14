package com.tbcacademy.shopapp.di

import android.content.Context
import com.squareup.okhttp.OkHttpClient
import com.tbcacademy.shopapp.BuildConfig
import com.tbcacademy.shopapp.data.UserPreference
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
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private fun httpClient(userPreference: UserPreference): okhttp3.OkHttpClient {
        val builder = okhttp3.OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            chain.request().url
            val request = chain.request().newBuilder()

            val token = userPreference.token()
            if (!token.isNullOrBlank()){
                request.addHeader("Authorization","Bearer $token")

            }
            val response = chain.proceed(request.build())
            response.body
            when (response.code) {
                401 ->{

                }
            }
            response
        })
        if (BuildConfig.BUILD_TYPE == "debug") {
            builder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
        return builder.build()
    }

 /*   @Singleton
    @Provides
    fun provideApi():PostService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PostService::class.java)
    }*/
    @Singleton
    @Provides
    fun postSetvice (@ApplicationContext context: Context,userPreference: UserPreference): PostService = Retrofit.Builder().baseUrl(
     BASE_URL
    ).addConverterFactory(GsonConverterFactory.create()).client(httpClient(userPreference)).build().create(PostService::class.java)



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