package com.pg13.myapp.domain.repositories

import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(isFavorite: Boolean): Flow<Resource<List<Post>>>

    suspend fun addPost(post: Post)
}