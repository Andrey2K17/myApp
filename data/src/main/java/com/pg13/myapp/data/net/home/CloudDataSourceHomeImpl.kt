package com.pg13.myapp.data.net.home

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.mappers.mapToDomain
import com.pg13.myapp.data.net.CloudDataSource
import com.pg13.myapp.data.util.networkBoundResource
import com.pg13.myapp.domain.entites.AlbumItem
import com.pg13.myapp.domain.entites.ItemHome
import com.pg13.myapp.domain.entites.PhotoItem
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip

class CloudDataSourceHomeImpl(
    val api: RemoteApi
) : CloudDataSource<ItemHome> {

    override fun getData(): Flow<Resource<List<ItemHome>>> = flow {
        emit(Resource.Loading())
            val albums =
                networkBoundResource({ api.getAlbums() }, { albums -> albums.map { it.mapToDomain() } })

        val photos =
            networkBoundResource({ api.getPhotos() }, { photos -> photos.map { it.mapToDomain() } })

        emitAll(
            albums.zip(photos) { albumsData, photosData ->
                val items = mutableListOf<ItemHome>()

                if (albumsData is Resource.Success) {
                    items.add(AlbumItem(albumsData.data))
                }

                if (photosData is Resource.Success) {
                    items.add(PhotoItem(photosData.data))
                }

                Resource.Success(items.toList())
            }.flowOn(Dispatchers.Default)
        )

    }
}