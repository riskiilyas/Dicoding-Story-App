package com.keecoding.storyapp.util

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.keecoding.storyapp.data.Repository
import com.keecoding.storyapp.data.local.StoryDatabase
import com.keecoding.storyapp.data.remote.APIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {

    // REPOSITORY DEPENDENCY //
    private var REPOSITORY: Repository? = null

    fun getRepository(app: Application): Repository {
        if (REPOSITORY == null) {
            REPOSITORY = Repository(getApiService(), getDatabase(app).storyDao())
        }
        return REPOSITORY!!
    }

    // SERVICE DEPENDENCY //
    private var SERVICE: APIService? = null

    private fun getApiService(): APIService {
        if (SERVICE == null) {
            val client = OkHttpClient.Builder()
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://story-api.dicoding.dev/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            SERVICE = retrofit.create(APIService::class.java)
        }

        return SERVICE!!
    }

    // DATABASE DEPENDENCY //
    @Volatile
    private var INSTANCE: StoryDatabase? = null

    @JvmStatic
    private fun getDatabase(context: Context): StoryDatabase {
        if (INSTANCE == null) {
            synchronized(StoryDatabase::class.java) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    StoryDatabase::class.java, "user_db"
                )
                    .build()
            }
        }
        return INSTANCE as StoryDatabase
    }

}