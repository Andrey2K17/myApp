package com.pg13.myapp.domain.repositories

import com.pg13.myapp.domain.entites.Comment
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun getCommentsById(id: Int): Flow<Resource<List<Comment>>>
}