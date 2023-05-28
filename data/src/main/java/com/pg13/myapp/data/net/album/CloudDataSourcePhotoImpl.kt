package com.pg13.myapp.data.net.album

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.mappers.mapToDomain
import com.pg13.myapp.data.net.CloudDataSource
import com.pg13.myapp.data.util.networkBoundResource
import com.pg13.myapp.domain.entites.Album
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourcePhotoImpl(
    private val api: RemoteApi
): CloudDataSource<Album> {
    override fun getData(): Flow<Resource<List<Album>>> = networkBoundResource(
        {api.getAlbums()},
        { data ->
            return@networkBoundResource data.map { it.mapToDomain() }
        }
    )
}