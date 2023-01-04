package com.keecoding.storyapp.data.remote

import com.keecoding.storyapp.data.remote.request.LoginBody
import com.keecoding.storyapp.data.remote.request.RegisterBody
import com.keecoding.storyapp.data.remote.response.ListStoriesResponse
import com.keecoding.storyapp.data.remote.response.LoginResponse
import com.keecoding.storyapp.data.remote.response.PostStoryResponse
import com.keecoding.storyapp.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIService {

    @POST("register")
    fun registerUser(@Body body: RegisterBody): Call<RegisterResponse>

    @POST("login")
    fun loginUser(@Body body: LoginBody): Call<LoginResponse>

    @Headers("Content-Type: multipart/form-data")
    @POST("stories")
    fun postStory(
        @Part file: MultipartBody.Part,
        @Part("description") desc: RequestBody,
        @Header("Authorization") token: String
    ): Call<PostStoryResponse>

    @GET("stories")
    fun getAllStories(@Header("Authorization") token: String): Call<ListStoriesResponse>
}