package com.pg13.myapp.mappers

import com.pg13.myapp.domain.entites.Photo
import com.pg13.myapp.entities.PhotoUI

fun Photo.mapToUI(): PhotoUI = PhotoUI(albumId, id, title, url, thumbnailUrl)