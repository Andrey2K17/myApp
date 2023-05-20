package com.pg13.myapp.data.db

import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

interface DataFetcher<E> {
    fun getData(): Flow<Resource<List<E>>>
}