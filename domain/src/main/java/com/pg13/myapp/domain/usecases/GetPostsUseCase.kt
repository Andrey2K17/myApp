package com.pg13.myapp.domain.usecases

import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.repositories.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(private val repository: PostRepository) {
    operator fun invoke(isFavorite: Boolean): Flow<Resource<List<Post>>> = repository.getPosts(isFavorite)
}