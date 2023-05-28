package com.pg13.myapp.domain.entites

data class Photo(
    val albumId: Int,
    override val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
): ItemHome

data class PhotoItem(
    var list: List<Photo>
): ItemHome {
    override val id: Int
        get() = list.size
}
