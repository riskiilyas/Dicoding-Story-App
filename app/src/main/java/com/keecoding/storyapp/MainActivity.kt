package com.keecoding.storyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keecoding.storyapp.util.ViewModelFactory
import com.keecoding.storyapp.util.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedViewModel = getViewModel(SharedViewModel::class.java)

        sharedViewModel.darkMode.observe(this) {
            AppCompatDelegate.setDefaultNightMode(if (it) MODE_NIGHT_YES else MODE_NIGHT_NO)
        }

        sharedViewModel.hasInternetConnection.observe(this) {
            Toast.makeText(this, "has Internet: $it", Toast.LENGTH_SHORT).show()
        }

        sharedViewModel.name.observe(this) {
            if (it.isNullOrBlank()) return@observe
            showToast("Welcome Back, $it!")
        }
    }

    fun <V : ViewModel> getViewModel(type: Class<V>): V {
        val factory = ViewModelFactory.getInstance(this)
        return ViewModelProvider(this, factory)[type]
    }
}