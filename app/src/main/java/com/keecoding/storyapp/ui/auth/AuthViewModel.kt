package com.keecoding.storyapp.ui.auth

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keecoding.storyapp.FragmentViewModel
import com.keecoding.storyapp.MainActivity
import com.keecoding.storyapp.data.remote.response.LoginResult
import com.keecoding.storyapp.data.remote.response.RegisterResponse
import com.keecoding.storyapp.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel(activity: MainActivity): FragmentViewModel(activity) {

    private val _registerResult: MutableLiveData<Resource<RegisterResponse>> = MutableLiveData(Resource.INIT())
    val registerResult = _registerResult as LiveData<Resource<RegisterResponse>>

    private val _loginResult: MutableLiveData<Resource<LoginResult>> = MutableLiveData(Resource.INIT())
    val loginResult = _loginResult as LiveData<Resource<LoginResult>>

    val token = sharedViewModel.token

    fun initRegisterResult() {
        _registerResult.postValue(Resource.INIT())
    }

    fun initLoginResult() {
        _loginResult.postValue(Resource.INIT())
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.register(name,email,password).collect {
                _registerResult.postValue(it)
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.signIn(email,password).collect {
                _loginResult.postValue(it)

                if (it is Resource.SUCCESS) {
                    dataStore.write(tokenKey, it.result.token)
                    dataStore.write(nameKey, it.result.name)
                }
            }
        }
    }
}