package com.keecoding.storyapp.ui.add_story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keecoding.storyapp.databinding.FragmentAddStoryBinding
import com.keecoding.storyapp.ui.BaseFragment

class AddStoryFragment : BaseFragment() {

    private var _binding: FragmentAddStoryBinding? = null
    private val binding get() = _binding as FragmentAddStoryBinding

    private lateinit var viewModel: AddStoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddStoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(AddStoryViewModel::class.java)
    }

    override fun setupArguments(arguments: Bundle?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}