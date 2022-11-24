package com.keecoding.storyapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.keecoding.storyapp.data.local.dto.Story
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {

    @Insert
    suspend fun insertStory(story: Story)

    @Query("SELECT * FROM ${Story.TABLE_NAME} WHERE id = :id")
    fun getStory(id: Int): Flow<Story>

    @Query("SELECT * FROM ${Story.TABLE_NAME}")
    fun getStories(): Flow<List<Story>>

    @Delete
    suspend fun deleteStory(story: Story)

    @Query("DELETE FROM ${Story.TABLE_NAME}")
    suspend fun deleteAllStories()

}