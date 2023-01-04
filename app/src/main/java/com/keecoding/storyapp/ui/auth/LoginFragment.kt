package com.keecoding.storyapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.keecoding.storyapp.R
import com.keecoding.storyapp.databinding.FragmentLoginBinding
import com.keecoding.storyapp.ui.BaseFragment
import com.keecoding.storyapp.util.Resource

class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding as FragmentLoginBinding

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(AuthViewModel::class.java)

        binding.apply {

            btnExit.setOnClickListener {
                requireActivity().finish()
            }

            tvSignUp.setOnClickListener {
                navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }

            btnRegis.setOnClickListener {
                if (etEmail.isReady && etPassword.isReady) {
                    viewModel.login(etEmail.text.toString(), etPassword.text.toString())
                } else {
                    Toast.makeText(requireContext(), resources.getText(R.string.name_is_empty), Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loginResult.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.LOADING -> {
                    showProgressDialog("Signing in...")
                }

                is Resource.ERROR -> {
                    closeProgressDialog()
                    showToast(it.msg)
                    clearForms()
                }

                is Resource.SUCCESS -> {
                    closeProgressDialog()
                    Toast.makeText(requireContext(), "Welcome ${it.result.name}!", Toast.LENGTH_SHORT).show()
                    navigate(LoginFragmentDirections.actionLoginFragmentToListStoryFragment())
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