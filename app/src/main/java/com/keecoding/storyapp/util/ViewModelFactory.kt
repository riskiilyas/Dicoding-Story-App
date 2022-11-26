package com.keecoding.storyapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keecoding.storyapp.MainActivity
import com.keecoding.storyapp.SharedViewModel
import com.keecoding.storyapp.ui.add_story.AddStoryViewModel
import com.keecoding.storyapp.ui.auth.AuthViewModel
import com.keecoding.storyapp.ui.detail.DetailViewModel
import com.keecoding.storyapp.ui.favorite.FavoriteViewModel
import com.keecoding.storyapp.ui.list_story.ListStoryViewModel

class ViewModelFactory(private val activity: MainActivity) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(activity: MainActivity): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(activity)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddStoryViewModel::class.java)) {
            return AddStoryViewModel(activity) as T
        } else if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(activity) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(activity) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(activity) as T
        } else if (modelClass.isAssignableFrom(ListStoryViewModel::class.java)) {
            return ListStoryViewModel(activity) as T
        }else if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(activity.application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}