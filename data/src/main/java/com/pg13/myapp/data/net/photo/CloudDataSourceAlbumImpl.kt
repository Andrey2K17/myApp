package com.pg13.myapp.data.net.photo

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.net.CloudDataSource
import com.pg13.myapp.data.util.networkBoundResource
import com.pg13.myapp.domain.entites.Photo
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourcePhotoImpl(
    private val api: RemoteApi
): CloudDataSource<Photo> {
    override fun getData(): Flow<Resource<List<Photo>>> = networkBoundResource(
        { api.getPhotos() },
        { data ->
            return@networkBoundResource data.map { Photo(it.albumId, it.id, it.title, it.url, it.thumbnailUrl) }
        }
    )
}