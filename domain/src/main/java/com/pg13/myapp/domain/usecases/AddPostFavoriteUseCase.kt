package com.pg13.myapp.domain.usecases

import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.repositories.PostRepository

class AddPostFavoriteUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(post: Post): Unit = repository.addPost(post)
}