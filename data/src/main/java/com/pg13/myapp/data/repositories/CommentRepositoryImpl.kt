package com.pg13.myapp.data.repositories

import com.pg13.myapp.data.net.comment.CloudDataSourceComment
import com.pg13.myapp.domain.entites.Comment
import com.pg13.myapp.domain.entites.Resource
import com.pg13.myapp.domain.repositories.CommentRepository
import kotlinx.coroutines.flow.Flow

class CommentRepositoryImpl(
    private val cloudDataSource: CloudDataSourceComment<Comment>
): CommentRepository {

    override fun getCommentsById(id: Int): Flow<Resource<List<Comment>>> = cloudDataSource.getDataById(id)
}