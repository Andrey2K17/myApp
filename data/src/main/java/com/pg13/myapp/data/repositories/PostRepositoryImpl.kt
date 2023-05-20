package com.pg13.myapp.data.repositories

import com.pg13.myapp.data.db.CacheDataSource
import com.pg13.myapp.data.net.CloudDataSource
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.repositories.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val cacheDataSource: CacheDataSource<Post>,
    private val cloudDataSource: CloudDataSource<Post>
): PostRepository {
    override fun getPosts(isFavorite: Boolean): Flow<Resource<List<Post>>> {
        val currentDataSource = if (isFavorite) cacheDataSource else cloudDataSource
        return currentDataSource.getData()
    }
    override suspend fun addPost(post: Post) {
        cacheDataSource.add(post)
    }
}