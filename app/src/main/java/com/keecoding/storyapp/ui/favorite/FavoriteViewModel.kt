package com.keecoding.storyapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keecoding.storyapp.FragmentViewModel
import com.keecoding.storyapp.MainActivity
import com.keecoding.storyapp.data.local.dto.Story
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(activity: MainActivity): FragmentViewModel(activity) {

    private val _favorites: MutableLiveData<List<Story>> = MutableLiveData(emptyList())
    val favorites get() = _favorites as LiveData<List<Story>>

    init {
        getFavorites()
    }

    fun deleteStory(story: Story) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.removeFavorite(story)
        }
    }

    fun deleteAllStory() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.removeAllFavorites()
        }
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.Default) {
            repository.getFavoriteStories().collect {
                _favorites.postValue(it)
            }
        }
    }
}