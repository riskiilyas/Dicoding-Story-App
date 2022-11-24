package com.keecoding.storyapp.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Story.TABLE_NAME)
data class Story(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val createdAt: String
) {
    companion object {
        const val TABLE_NAME = "story"
    }
}
