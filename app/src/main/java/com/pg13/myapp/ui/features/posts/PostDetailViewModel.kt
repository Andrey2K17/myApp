package com.pg13.myapp.ui.features.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg13.myapp.domain.entites.Comment
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.usecases.GetCommentsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getCommentsByIdUseCase: GetCommentsByIdUseCase
): ViewModel() {

    private val refreshEvent = MutableSharedFlow<Unit>(1)
    private var id: Int = 0

    val comments: Flow<Resource<List<Comment>>> =
        refreshEvent
            .flatMapLatest { getCommentsByIdUseCase(id) }

    fun updateComment(id: Int) {
        this.id = id
        viewModelScope.launch {
            refreshEvent.emit(Unit)
            cancel()
        }
    }
}