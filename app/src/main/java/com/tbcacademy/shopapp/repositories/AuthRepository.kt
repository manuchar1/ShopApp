package com.tbcacademy.shopapp.repositories

import com.google.firebase.auth.AuthResult
import com.tbcacademy.shopapp.utils.Resource



interface AuthRepository {

    suspend fun register(email:String,username:String,password:String): Resource<AuthResult>

    suspend fun login(email:String,password:String):Resource<AuthResult>
}