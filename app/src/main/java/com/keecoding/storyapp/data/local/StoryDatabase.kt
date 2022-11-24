package com.keecoding.storyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keecoding.storyapp.data.local.dto.Story

@Database(entities = [Story::class], version = 1)
abstract class StoryDatabase: RoomDatabase() {
    abstract fun storyDao(): StoryDao
}