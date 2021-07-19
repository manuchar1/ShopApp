package com.tbcacademy.shopapp.repositories.posts

import com.google.gson.Gson
import com.tbcacademy.shopapp.base.safeCall
import com.tbcacademy.shopapp.network.models.Post
import com.tbcacademy.shopapp.network.PostService
import com.tbcacademy.shopapp.utils.Resource

class PostRepositoryImpl(private val postService: PostService):PostRepository {
    override suspend fun getPosts(): Resource<List<Post>> {
        return safeCall {

            val response = postService.getPosts()
            if (response.isSuccessful){
                val body = response.body()!!
                Resource.Success(body)
            }else {
                val error = Gson().fromJson(response.errorBody()!!.string(),Error::class.java)
                Resource.Error(error.toString())
            }

        }
    }
}