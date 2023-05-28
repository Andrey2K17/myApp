package com.pg13.myapp.data.db

import com.pg13.myapp.data.entities.local.PostLocal
import com.pg13.myapp.data.mappers.mapToDomain
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CacheDataSourceImpl(
    private val postDao: PostDao
): CacheDataSource<Post> {
    override suspend fun getDataList(): List<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun add(item: Post) {
        val post = PostLocal(item.id, item.userId, item.title, item.body, item.favorite)
        if (post.favorite) {
            postDao.savePost(post)
        } else {
            postDao.removePost(post)
        }
    }

    override fun getData(): Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading())

        val posts = postDao.getAll()

        emit(Resource.Success(posts.map { it.mapToDomain() }))
    }
}