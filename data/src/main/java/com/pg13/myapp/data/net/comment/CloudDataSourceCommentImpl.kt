package com.pg13.myapp.data.net.comment

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.entities.remote.CommentRemote
import com.pg13.myapp.data.mappers.mapToDomain
import com.pg13.myapp.data.util.networkBoundResource
import com.pg13.myapp.domain.entites.Comment
import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow

class CloudDataSourceCommentImpl(
    private val api: RemoteApi
) : CloudDataSourceComment<Comment> {
    override fun getDataById(id: Int): Flow<Resource<List<Comment>>> = networkBoundResource(
        {api.getCommentsByPostId(id)},
        { cloudData ->

            return@networkBoundResource cloudData.map<CommentRemote, Comment> {it.mapToDomain() }
        }
    )

}