package com.pg13.myapp.domain.usecases

import com.pg13.myapp.domain.entites.ItemHome
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetHomeItemsUseCase(private val repository: HomeRepository) {
    operator fun invoke(): Flow<Resource<List<ItemHome>>> = repository.getHomeItems()
}