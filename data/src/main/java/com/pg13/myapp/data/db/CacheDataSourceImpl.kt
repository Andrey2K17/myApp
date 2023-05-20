package com.pg13.myapp.data.db

import com.pg13.myapp.data.entities.local.PostLocal
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

    override suspend fun addOrRemove() {
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
        val mapPosts = posts.map { Post(it.userId, it.id, it.title, it.body, it.favorite) }

        emit(Resource.Success(mapPosts))
    }

    override suspend fun remove(item: Post) {
        TODO("Not yet implemented")
    }
}