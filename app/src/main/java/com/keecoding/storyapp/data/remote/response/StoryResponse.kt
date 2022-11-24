package com.keecoding.storyapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.keecoding.storyapp.data.local.dto.Story

data class StoryResponse(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String,
    @SerializedName("photoUrl")
    val photoUrl: String
) {
    fun toStoryEntity()
        = Story(id,name,description,photoUrl,createdAt)
}