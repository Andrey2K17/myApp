package com.pg13.myapp.data.db

interface CacheDataSource<E>: DataFetcher<E> {
    suspend fun getDataList(): List<E>

    suspend fun add(item: E)
}