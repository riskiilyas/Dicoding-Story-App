package com.keecoding.storyapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat.getSystemService
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keecoding.storyapp.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SharedViewModel(private val app: Application): ViewModel() {

    private val _hasInternetConnection: MutableLiveData<Boolean> = MutableLiveData(false)
    val hasInternetConnection get() = _hasInternetConnection as LiveData<Boolean>

    private val _darkMode: MutableLiveData<Boolean> = MutableLiveData(false)
    val darkMode = _darkMode as LiveData<Boolean>

    private val _name: MutableLiveData<String> = MutableLiveData("")
    val name get() = _name as LiveData<String>

    private val _notificatoin: MutableLiveData<Boolean> = MutableLiveData(false)
    val notification = _notificatoin as LiveData<Boolean>

    private val _token: MutableLiveData<String> = MutableLiveData("")
    val token get() = _token as LiveData<String>

    init {
        readTheme()
        setupNetworkListener()
    }

    fun getDataStore() = app.dataStore

    private fun readTheme() {
        viewModelScope.launch(Dispatchers.Default) {
            app.dataStore.read(themeKey).collect {
                _darkMode.postValue(it?:false)
            }
        }
    }

    private fun readName() {
        viewModelScope.launch(Dispatchers.Default) {
            app.dataStore.read(nameKey).collect {
                _name.postValue(it?:"")
            }
        }
    }

    private fun readToken() {
        viewModelScope.launch(Dispatchers.Default) {
            app.dataStore.read(tokenKey).collect {
                _token.postValue(it?:"")
            }
        }
    }

    private fun readNotifcation() {
        viewModelScope.launch(Dispatchers.Default) {
            app.dataStore.read(notificationKey).collect {
                _darkMode.postValue(it ?: false)
            }
        }
    }


    private fun setupNetworkListener() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _hasInternetConnection.postValue(true)
            }

            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
                _hasInternetConnection.postValue(unmetered)
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                _hasInternetConnection.postValue(false)
            }
        }

        val connectivityManager = app.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

}