package com.keecoding.storyapp.data

import com.keecoding.storyapp.data.local.StoryDao
import com.keecoding.storyapp.data.local.dto.Story
import com.keecoding.storyapp.data.remote.APIService
import com.keecoding.storyapp.util.Errorable
import com.keecoding.storyapp.data.remote.request.LoginBody
import com.keecoding.storyapp.data.remote.request.RegisterBody
import com.keecoding.storyapp.util.Resource
import com.keecoding.storyapp.util.toStoryEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.awaitResponse
import java.io.File

class Repository(
    private val service: APIService,
    private val storyDao: StoryDao
) {

    // REMOTE REQUEST //

    suspend fun signIn(email: String, password: String)
    = startFetch(
            service.loginUser(LoginBody(email,password)),
            { Errorable(it.error, it.message) },
            {it.loginResult}
        )

    suspend fun register(name: String, email: String, password: String)
    = startFetch(
           service.registerUser(RegisterBody(name,email,password)),
           { Errorable(it.error, it.message) },
           {it}
       )

    suspend fun getAllStories(token: String)
    = startFetch(
           service.getAllStories("Bearer $token"),
           { Errorable(it.error, it.message) },
           { it.listStory.toStoryEntities() }
       )

    suspend fun postStory(file: File, desc: String, token: String)
    = run {
        val fileReq = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("photo", file.name, fileReq)
        val descPart = desc.toRequestBody("text/plain".toMediaType())

        startFetch(
            service.postStory(filePart, descPart, "Bearer $token"),
            { Errorable(it.error, it.message) },
            {it}
        )
    }

    // DATABASE QUERY //

    fun getFavoriteStories() = storyDao.getStories()

    suspend fun removeFavorite(story: Story) {
        storyDao.deleteStory(story)
    }

    suspend fun removeAllFavorites() {
        storyDao.deleteAllStories()
    }

    private suspend fun <T, R> startFetch(
        fetch: Call<T>,
        checkError: (T) -> Errorable,
        filter: (T) -> R
    ) = flow {
        emit(Resource.LOADING())
        try {
            val response = fetch.awaitResponse()
            val data = response.body()

            if (data != null) {
                val isError = checkError(data)
                if (isError.error) {
                    emit(Resource.ERROR(isError.message))
                } else {
                    emit(Resource.SUCCESS(filter(data)))
                }
            } else {
                emit(Resource.ERROR("No Response Error!"))
            }
        } catch (e: Exception) {
            emit(Resource.ERROR(e.message?:"Unknown Error!"))
        }
    }.flowOn(Dispatchers.IO)

}