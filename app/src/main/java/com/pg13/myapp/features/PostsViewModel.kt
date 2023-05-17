package com.pg13.myapp.features

import androidx.lifecycle.ViewModel
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    getPostsUseCase: GetPostsUseCase
): ViewModel() {

    val posts: Flow<Resource<List<Post>>> = getPostsUseCase()
}