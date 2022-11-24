package com.keecoding.storyapp.ui.list_story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keecoding.storyapp.databinding.FragmentListStoryBinding
import com.keecoding.storyapp.ui.BaseFragment

class ListStoryFragment : BaseFragment() {

    private var _binding: FragmentListStoryBinding? = null
    private val binding get() = _binding as FragmentListStoryBinding

    private lateinit var viewModel: ListStoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListStoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = mainActivity.getViewModel(ListStoryViewModel::class.java)

        

        viewModel.stories.observe(viewLifecycleOwner) {

        }
    }

    override fun setupArguments(arguments: Bundle?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}