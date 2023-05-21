package com.pg13.myapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentUI(
    val postId: Int, val id: Int, val name: String, val email: String, val body: String
) : Parcelable
