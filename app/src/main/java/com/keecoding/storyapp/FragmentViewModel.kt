package com.keecoding.storyapp

import androidx.lifecycle.ViewModel
import com.keecoding.storyapp.util.ServiceLocator

abstract class FragmentViewModel(activity: MainActivity): ViewModel() {
    protected val repository = ServiceLocator.getRepository(activity.application)
    protected val sharedViewModel = activity.getViewModel(SharedViewModel::class.java)
    protected val dataStore = sharedViewModel.getDataStore()
}