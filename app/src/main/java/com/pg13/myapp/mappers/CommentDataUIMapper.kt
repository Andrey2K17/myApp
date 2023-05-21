package com.pg13.myapp.mappers

import com.pg13.myapp.domain.entites.Comment
import com.pg13.myapp.entities.CommentUI

fun Comment.mapToUI(): CommentUI {
    return CommentUI(postId, id, name, email, body)
}