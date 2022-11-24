package com.keecoding.storyapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.keecoding.storyapp.data.local.StoryDatabase
import com.keecoding.storyapp.data.remote.APIService

data class LoginResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("loginResult")
    val loginResult: LoginResult,
    @SerializedName("message")
    val message: String
)