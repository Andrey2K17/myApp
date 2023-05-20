package com.pg13.myapp.ui.features.users

import androidx.lifecycle.ViewModel
import com.pg13.myapp.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
) : ViewModel() {
    fun getPosts(isFavorite: Boolean) = getPostsUseCase.invoke(isFavorite)
}