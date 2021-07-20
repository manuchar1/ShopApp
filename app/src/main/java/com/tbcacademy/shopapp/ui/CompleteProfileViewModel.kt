package com.tbcacademy.shopapp.ui

import android.content.Context
import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CompleteProfileViewModel @ViewModelInject constructor(
    private val applicationContext: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

) : ViewModel() {

    private val _curImageUri = MutableLiveData<Uri>()
    val curImageUri: LiveData<Uri> = _curImageUri



    fun setCurImageUri(uri: Uri) {
        _curImageUri.postValue(uri)
    }

}