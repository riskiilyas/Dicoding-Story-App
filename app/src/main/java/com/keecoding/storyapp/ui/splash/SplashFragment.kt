package com.keecoding.storyapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.keecoding.storyapp.R
import com.keecoding.storyapp.SharedViewModel
import com.keecoding.storyapp.databinding.FragmentSplashBinding
import com.keecoding.storyapp.ui.BaseFragment
import kotlinx.coroutines.delay

class SplashFragment : BaseFragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding as FragmentSplashBinding

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = getViewModel(SharedViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            delay(1500)
            sharedViewModel.name.observe(viewLifecycleOwner) {
                if (it == null) {
                    navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                } else {
                    navigate(SplashFragmentDirections.actionSplashFragmentToListStoryFragment())
                }
            }
        }
    }

    override fun setupArguments(arguments: Bundle?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}