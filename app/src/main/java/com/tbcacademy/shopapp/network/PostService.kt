package com.tbcacademy.shopapp.network





import com.tbcacademy.shopapp.network.models.Post
import com.tbcacademy.shopapp.utils.Constants.POSTS_API_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET


interface PostService {

    @GET(POSTS_API_ENDPOINT)
    suspend fun getPosts(): Response<List<Post>>


}