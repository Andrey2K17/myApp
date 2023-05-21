package com.pg13.myapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostUI(
    val userId: Int, val id: Int, val title: String, val body: String, var favorite: Boolean = false
): Parcelable