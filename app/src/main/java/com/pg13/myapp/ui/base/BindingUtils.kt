package com.pg13.myapp.ui.base

import android.widget.CheckedTextView
import androidx.databinding.BindingAdapter

object BindingUtils {
    @JvmStatic
    @BindingAdapter("resChecked", "resUnchecked")
    fun CheckedTextView.checkboxButton(resChecked: Int?, resUnchecked: Int?, ) {
        setCheckMarkDrawable(resChecked?:0)
    }
/*    @JvmStatic
    @BindingAdapter("resChecked", "resUnchecked", "checkedChange")
    fun CheckedTextView.checkboxButton(resChecked: Int?, resUnchecked: Int?, checkedChange: Boolean?) {
        setCheckMarkDrawable(if (checkedChange == true) resChecked?:0 else resUnchecked?:0)
    }*/
}