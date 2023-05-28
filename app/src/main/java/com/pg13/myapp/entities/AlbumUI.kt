package com.pg13.myapp.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumUI(val userId : Int, val id: Int, val title: String) : Parcelable
