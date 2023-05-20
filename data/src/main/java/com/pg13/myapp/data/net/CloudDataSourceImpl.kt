package com.pg13.myapp.data.net

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.db.PostDao
import com.pg13.myapp.data.util.networkBoundResource
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourceImpl(
    private val postApi: RemoteApi,
    private val postDao: PostDao
): CloudDataSource<Post> {

    override fun getData(): Flow<Resource<List<Post>>> = networkBoundResource(
        {postApi.getPosts()},
        {postDao.getAll()},
        { cloudData, cachedData ->
            val postsCloud = cloudData.map { Post(it.userId, it.id, it.title, it.body) }

            val posts = postsCloud.onEach {
                it.favorite = cachedData.any{localPost -> localPost.id == it.id}
            }

            return@networkBoundResource posts
        }
    )
}