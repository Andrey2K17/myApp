package com.pg13.myapp.ui.features.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.usecases.AddPostFavoriteUseCase
import com.pg13.myapp.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val addPostFavoriteUseCase: AddPostFavoriteUseCase,
    ): ViewModel() {

    private val refreshEvent = MutableSharedFlow<Unit>(1)
    private var _isFavorite: Boolean = false

    val isFavorite get() = _isFavorite
    init {
        updatePosts(_isFavorite)
    }

    val posts: Flow<Resource<List<Post>>> =
        refreshEvent
            .flatMapLatest { getPostsUseCase(_isFavorite) }



    fun addPostFavorite(post: Post) {
        viewModelScope.launch {
            addPostFavoriteUseCase.invoke(post)
            if (!post.favorite && _isFavorite){
                refreshEvent.emit(Unit)
            }
        }
    }

    fun updatePosts(isFavorite: Boolean? = false) {
        isFavorite?.let { _isFavorite = it }
        viewModelScope.launch {
            refreshEvent.emit(Unit)
            cancel()
        }
    }
}