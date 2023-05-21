package com.pg13.myapp.data.net

import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

interface CloudDataSourceComment<E> {
    fun getDataById(id: Int): Flow<Resource<List<E>>>
}