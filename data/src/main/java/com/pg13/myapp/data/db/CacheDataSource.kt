package com.pg13.myapp.data.db

interface CacheDataSource<E>: DataFetcher<E> {
    suspend fun getDataList(): List<E>

    suspend fun remove(item: E)

    suspend fun addOrRemove()

    suspend fun add(item: E)
}