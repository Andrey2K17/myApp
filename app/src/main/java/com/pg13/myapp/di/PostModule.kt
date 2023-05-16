package com.pg13.myapp.di

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.repositories.PostRepositoryImpl
import com.pg13.myapp.domain.repositories.PostRepository
import com.pg13.myapp.domain.usecases.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PostModule {

    @Singleton
    @Provides
    fun provideClient(retrofit: Retrofit): RemoteApi {
        return retrofit.create(RemoteApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGetPostsUseCase(repository: PostRepository): GetPostsUseCase {
        return GetPostsUseCase(repository)
    }

    @Singleton
    @Provides
    fun providePostsRepository(client: RemoteApi): PostRepository {
        return PostRepositoryImpl(client)
    }
}