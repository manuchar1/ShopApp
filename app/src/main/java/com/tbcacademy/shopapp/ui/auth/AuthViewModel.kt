package com.tbcacademy.shopapp.ui.auth

import android.content.Context
import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.tbcacademy.shopapp.R
import com.tbcacademy.shopapp.repositories.AuthRepository
import com.tbcacademy.shopapp.utils.Constants.MAX_USERNAME_LENGTH
import com.tbcacademy.shopapp.utils.Constants.MIN_PASSWORD_LENGHT
import com.tbcacademy.shopapp.utils.Constants.MIN_USERNAME_LENGTH
import com.tbcacademy.shopapp.utils.Event
import com.tbcacademy.shopapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class AuthViewModel @ViewModelInject constructor(
    private val repository: AuthRepository,
    private val applicationContext: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _registerStatus = MutableLiveData<Event<Resource<AuthResult>>>()
    val registerStatus: LiveData<Event<Resource<AuthResult>>> = _registerStatus

    fun register(email: String, username: String, password: String, confirmPassword: String) {
        val error = when {
            email.isEmpty() || username.isEmpty() || password.isEmpty() -> applicationContext.getString(
                R.string.error_input_empty
            )
            password != confirmPassword -> applicationContext.getString(
                R.string.error_incorrectly_repeated_password
            )
            username.length < MIN_USERNAME_LENGTH -> applicationContext.getString(
                R.string.error_username_too_short, MIN_USERNAME_LENGTH
            )
            username.length > MAX_USERNAME_LENGTH -> applicationContext.getString(
                R.string.error_username_too_long, MAX_USERNAME_LENGTH
            )
            password.length < MIN_PASSWORD_LENGHT -> applicationContext.getString(
                R.string.error_password_too_short, MIN_PASSWORD_LENGHT
            )
            (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) -> applicationContext.getString(
                R.string.error_not_a_valid_email
            )

            else -> null
        }

        error?.let {
            _registerStatus.postValue(Event(Resource.Error(it)))
            return
        }
        _registerStatus.postValue(Event(Resource.Loading()))
        viewModelScope.launch(dispatcher) {
            val result = repository.register(email,username,password)
            _registerStatus.postValue(Event(result))

        }
    }
}