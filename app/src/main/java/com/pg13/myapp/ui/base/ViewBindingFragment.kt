package com.pg13.myapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

const val TITLE = "title"

abstract class ViewBindingFragment<ViewBinding : ViewDataBinding> : Fragment() {

    protected val TAG = javaClass.simpleName

    protected lateinit var binding: ViewBinding

    protected abstract fun getLayoutId(): Int

    open val showSupportActionBar: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        //Timber.tag("FragmentLifecycle").d("${this.javaClass.simpleName}: onCreate")
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Timber.tag("FragmentLifecycle").d("${this.javaClass.simpleName}: onCreateView")
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            if (showSupportActionBar) {
                if (!isShowing) show()
            } else {
                if (isShowing) hide()
            }
        }

        return binding.root
    }

    override fun onStart() {
        //Timber.tag("FragmentLifecycle").d("${this.javaClass.simpleName}: onStart")
        super.onStart()
    }

    override fun onResume() {
        //Timber.tag("FragmentLifecycle").d("${this.javaClass.simpleName}: onResume")
        super.onResume()
        //FirebaseCrashlytics.getInstance().setCustomKey(FirebaseKeys.CrashKeys.LAST_RESUMED_FRAG, javaClass.simpleName)
    }

    override fun onPause() {
        //Timber.tag("FragmentLifecycle").d("${this.javaClass.simpleName}: onPause")
        super.onPause()
       //requireView().hideKeyboard()
    }

    override fun onStop() {
        //Timber.tag("FragmentLifecycle").d("${this.javaClass.simpleName}: onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        //Timber.tag("FragmentLifecycle").d("${this.javaClass.simpleName}: onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        //Timber.tag("FragmentLifecycle").d("${this.javaClass.simpleName}: onDestroy")
        super.onDestroy()
    }
}