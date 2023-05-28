package com.pg13.myapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoUI(
    val albumId: Int, val id: Int, val title: String, val url: String, val thumbnailUrl: String
) : Parcelable
