package com.keecoding.storyapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.keecoding.storyapp.R
import com.keecoding.storyapp.databinding.FragmentRegisterBinding
import com.keecoding.storyapp.ui.BaseFragment
import com.keecoding.storyapp.util.Resource

class RegisterFragment : BaseFragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding as FragmentRegisterBinding

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnRegis.setOnClickListener {
                if (etEmail.isReady && etName.isReady && etPassword.isReady) {
                    Toast.makeText(requireContext(), "Bisaaa", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Nggak", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel = getViewModel(AuthViewModel::class.java)

        viewModel.registerResult.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.LOADING -> {
                    showProgressDialog("Registering....")
                }

                is Resource.ERROR -> {
                    closeProgressDialog()
                    showToast(it.msg)
                    clearForms()
                }

                is Resource.SUCCESS -> {
                    closeProgressDialog()
                    showToast("Your Account is registered successfully!")
                    navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                }

                else -> {}
            }
        }
    }

    private fun clearForms() {

    }

    override fun setupArguments(arguments: Bundle?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}