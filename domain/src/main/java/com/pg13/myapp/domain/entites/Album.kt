package com.pg13.myapp.domain.entites

data class Album(val userId: Int, val id: Int, val title: String)

data class AlbumItem(
    var list: List<Album>
): ItemHome {
    override val id: Int
        get() = list.size
}