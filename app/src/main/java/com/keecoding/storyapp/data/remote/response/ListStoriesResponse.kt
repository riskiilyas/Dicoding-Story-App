package com.keecoding.storyapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class ListStoriesResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("listStory")
    val listStory: List<StoryResponse>,
    @SerializedName("message")
    val message: String
)