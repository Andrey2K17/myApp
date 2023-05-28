package com.pg13.myapp.ui.base

import android.widget.CheckedTextView
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

object BindingUtils {

    @JvmStatic
    @BindingAdapter("resChecked", "resUnchecked", "checkedChange")
    fun CheckedTextView.checkboxButton(resChecked: Int?, resUnchecked: Int?, checkedChange: Boolean?) {
        setCheckMarkDrawable(if (checkedChange == true) resChecked?:0 else resUnchecked?:0)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.bindImage(imageUrl: String?) {
        imageUrl?.let {
            val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
            load(imgUri)
        }
    }

}
