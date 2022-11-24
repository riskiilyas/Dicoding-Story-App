package com.keecoding.storyapp.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.keecoding.storyapp.data.local.dto.Story
import com.keecoding.storyapp.data.remote.response.StoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
val themeKey = booleanPreferencesKey("theme")
val tokenKey = stringPreferencesKey("token")
val nameKey = stringPreferencesKey("name")
val notificationKey = booleanPreferencesKey("notification")

fun <T> DataStore<Preferences>.read(prefKey: Preferences.Key<T>): Flow<T?> {
    return data.map { preferences ->
            preferences[prefKey]
        }
}

suspend fun <T> DataStore<Preferences>.write(prefKey: Preferences.Key<T>, value: T) {
    edit { settings ->
        settings[prefKey] = value
    }
}

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun List<StoryResponse>.toStoryEntities(): List<Story> {
    val list = mutableListOf<Story>()

    forEach {
        list.add(it.toStoryEntity())
    }

    return list
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}