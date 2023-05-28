package com.pg13.myapp.data.repositories

import com.pg13.myapp.data.net.CloudDataSource
import com.pg13.myapp.domain.entites.ItemHome
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val cloudDataSource: CloudDataSource<ItemHome>
): HomeRepository {
    override fun getHomeItems(): Flow<Resource<List<ItemHome>>> = cloudDataSource.getData()
}