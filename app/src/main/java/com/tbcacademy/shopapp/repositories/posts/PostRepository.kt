package com.tbcacademy.shopapp.repositories.posts

import com.tbcacademy.shopapp.models.Post
import com.tbcacademy.shopapp.utils.Resource

interface PostRepository {

    suspend fun getPosts() : Resource<List<Post>>
}