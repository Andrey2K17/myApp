package com.pg13.myapp.data.repositories

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.util.networkBoundResource
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.repositories.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val postApi: RemoteApi
): PostRepository {

    override fun getPosts() : Flow<Resource<List<Post>>> = networkBoundResource(
        {postApi.getPosts()},
        { postsData ->
            val posts = postsData.map { Post(it.userId, it.id, it.title, it.body) }

            return@networkBoundResource posts
        }
    )
}