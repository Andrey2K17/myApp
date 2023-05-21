package com.pg13.myapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pg13.myapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun View.setVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

inline fun LifecycleOwner.launchOnLifecycle(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend (CoroutineScope) -> Unit,
): Job {
    return lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(state) {
            block.invoke(this)
        }
    }
}

fun Context.showErrorDialog(message: String) {
    MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_MaterialAlertDialog).apply {
        setTitle(R.string.dialog_title_error)
        setMessage(message)
        setPositiveButton(R.string.dialog_button_ok) { dialog, which ->
            dialog.dismiss()
        }
    }.show()
}

fun Fragment.showErrorDialog(message: String) = requireContext().showErrorDialog(message)