package com.pg13.myapp.domain.usecases

import com.pg13.myapp.domain.entites.Comment
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.repositories.CommentRepository
import kotlinx.coroutines.flow.Flow

class GetCommentsByIdUseCase(private val repository: CommentRepository) {
    operator fun invoke(id: Int): Flow<Resource<List<Comment>>> = repository.getCommentsById(id)
}