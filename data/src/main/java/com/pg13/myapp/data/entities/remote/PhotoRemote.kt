package com.pg13.myapp.data.entities.remote

data class PhotoRemote(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
