package com.keecoding.storyapp.ui.list_story

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.keecoding.storyapp.FragmentViewModel
import com.keecoding.storyapp.MainActivity
import com.keecoding.storyapp.data.local.dto.Story
import com.keecoding.storyapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListStoryViewModel(activity: MainActivity): FragmentViewModel(activity) {

    private val _stories: MutableLiveData<Resource<List<Story>>> = MutableLiveData(Resource.INIT())
    val stories get() = _stories as LiveData<Resource<List<Story>>>

    fun getStories() {
        viewModelScope.launch(Dispatchers.Default) {
            val token = sharedViewModel.token.value
            if (token == null) {
                _stories.postValue(Resource.ERROR("Not Logged in!"))
            } else {
                repository.getAllStories(token).collect {
                    _stories.postValue(it)
                }
            }
        }
    }

}