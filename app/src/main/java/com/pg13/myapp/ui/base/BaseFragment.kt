package com.pg13.myapp.ui.base

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pg13.myapp.utils.hideKeyboard

abstract class BaseFragment<B: ViewDataBinding> : Fragment() {
    private var dialog: Dialog? = null

    lateinit var binding: B

    abstract fun getLayoutResource(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    fun finishFragment() {
        hideKeyboard()
        activity?.finishAfterTransition()
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    fun Fragment.getNavController(): NavController? {
        return try {
            NavHostFragment.findNavController(this)
        } catch (e: IllegalStateException) {
            null
        }
    }

    fun navigateUp() {
        getNavController()?.navigateUp()
    }
}