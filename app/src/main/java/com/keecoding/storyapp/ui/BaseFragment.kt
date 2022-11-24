package com.keecoding.storyapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.keecoding.storyapp.MainActivity
import com.keecoding.storyapp.R

abstract class BaseFragment : Fragment() {

    val mainActivity get() = (activity as MainActivity)
    private var _progressDialog : Dialog? = null

    abstract fun setupArguments(arguments: Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArguments(arguments)
    }

    protected fun <V : ViewModel> getViewModel(theClass: Class<V>): V {
        return (activity as MainActivity).getViewModel(theClass)
    }

    protected fun navigate(nav: NavDirections) {
        findNavController().navigate(nav)
    }

    protected fun showProgressDialog(msg: String = "Loading...") {
        _progressDialog = Dialog(requireContext()).apply {
            setContentView(R.layout.layout_progress)
            window?.setBackgroundDrawableResource(R.color.transparent)
            view?.let {
                findViewById<TextView>(R.id.tvLoadingText).text = msg
            }
            show()
        }
    }

    protected fun closeProgressDialog() {
        if (_progressDialog == null) return
        _progressDialog?.dismiss()
        _progressDialog = null
    }

    protected fun showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), msg, duration).show()
    }

    protected fun popBackStack() {
        findNavController().popBackStack()
    }

}