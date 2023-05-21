package com.pg13.myapp.di

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.net.CloudDataSourceComment
import com.pg13.myapp.data.net.CloudDataSourceCommentImpl
import com.pg13.myapp.data.repositories.CommentRepositoryImpl
import com.pg13.myapp.domain.entites.Comment
import com.pg13.myapp.domain.repositories.CommentRepository
import com.pg13.myapp.domain.usecases.GetCommentsByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommentModule {

    @Singleton
    @Provides
    fun provideGetCommentByIdUseCase(repository: CommentRepository): GetCommentsByIdUseCase {
        return GetCommentsByIdUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideCommentRepository(cloudDataSourceComment: CloudDataSourceComment<Comment>): CommentRepository {
        return CommentRepositoryImpl(cloudDataSourceComment)
    }

    @Singleton
    @Provides
    fun provideCloudDataSource(client: RemoteApi): CloudDataSourceComment<Comment> {
        return CloudDataSourceCommentImpl(client)
    }
}