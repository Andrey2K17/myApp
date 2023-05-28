package com.pg13.myapp.domain.repositories

import com.pg13.myapp.domain.entites.ItemHome
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getHomeItems(): Flow<Resource<List<ItemHome>>>
}