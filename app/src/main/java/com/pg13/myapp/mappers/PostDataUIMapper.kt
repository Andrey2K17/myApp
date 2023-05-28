package com.pg13.myapp.mappers

import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.entities.PostUI

fun Post.mapToUI(): PostUI {
    return PostUI(userId, id, title, body, favorite)
}

fun PostUI.mapToDomain(): Post {
    return Post(userId, id, title, body, favorite)
}