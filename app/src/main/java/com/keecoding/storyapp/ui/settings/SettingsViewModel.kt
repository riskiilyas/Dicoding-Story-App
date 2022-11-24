package com.keecoding.storyapp.ui.settings

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keecoding.storyapp.FragmentViewModel
import com.keecoding.storyapp.MainActivity
import com.keecoding.storyapp.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(activity: MainActivity): FragmentViewModel(activity) {

    val darkMode = sharedViewModel.darkMode
    val notification = sharedViewModel.notification
    val token = sharedViewModel.token
    val name = sharedViewModel.name

    fun logout() {
        viewModelScope.launch {
            dataStore.write(tokenKey, "")
            dataStore.write(nameKey, "")
        }
    }

    fun toggleTheme(isDark: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            dataStore.write(themeKey, isDark)
        }
    }

    fun toggleNotification(isOn: Boolean) {
        viewModelScope.launch(Dispatchers.Default) {
            dataStore.write(notificationKey, isOn)
        }
    }

}