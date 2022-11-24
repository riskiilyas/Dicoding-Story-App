package com.keecoding.storyapp.data.remote.request

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)