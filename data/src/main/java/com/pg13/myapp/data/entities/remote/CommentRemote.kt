package com.pg13.myapp.data.entities.remote

data class CommentRemote(
    val postId: Int, val id: Int, val name: String, val email: String, val body: String
)