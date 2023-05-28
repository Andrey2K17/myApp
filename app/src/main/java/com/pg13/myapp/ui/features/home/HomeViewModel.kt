package com.pg13.myapp.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg13.myapp.domain.entites.ItemHome
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.usecases.GetHomeItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeItemsUseCase: GetHomeItemsUseCase
) : ViewModel() {
    private val refreshEvent = MutableSharedFlow<Unit>(1)

    init {
        update()
    }

    val items: Flow<Resource<List<ItemHome>>> =
        refreshEvent
            .flatMapLatest { getHomeItemsUseCase() }

    fun update() {
        viewModelScope.launch {
            refreshEvent.emit(Unit)
            cancel()
        }
    }
}