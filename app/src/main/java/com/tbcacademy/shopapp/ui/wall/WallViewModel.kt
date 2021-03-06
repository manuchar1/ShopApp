package com.tbcacademy.shopapp.ui.wall

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbcacademy.shopapp.network.models.Post
import com.tbcacademy.shopapp.repositories.posts.PostRepository
import com.tbcacademy.shopapp.utils.Event
import com.tbcacademy.shopapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WallViewModel @ViewModelInject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _postLiveData = MutableLiveData<Event<Resource<List<Post>>>>()
    val postLiveData: LiveData<Event<Resource<List<Post>>>> = _postLiveData

    fun getPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _postLiveData.postValue(Event(postRepository.getPosts()))
            }

        }

    }


}