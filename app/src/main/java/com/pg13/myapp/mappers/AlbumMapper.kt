package com.pg13.myapp.mappers

import com.pg13.myapp.domain.entites.Album
import com.pg13.myapp.entities.AlbumUI

fun Album.mapToUI(): AlbumUI = AlbumUI(userId, id, title)
