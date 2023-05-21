package com.pg13.myapp.di

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.db.CacheDataSource
import com.pg13.myapp.data.db.CacheDataSourceImpl
import com.pg13.myapp.data.db.Database
import com.pg13.myapp.data.db.PostDao
import com.pg13.myapp.data.net.CloudDataSource
import com.pg13.myapp.data.net.CloudDataSourcePostImpl
import com.pg13.myapp.data.repositories.PostRepositoryImpl
import com.pg13.myapp.domain.entites.Post
import com.pg13.myapp.domain.repositories.PostRepository
import com.pg13.myapp.domain.usecases.AddPostFavoriteUseCase
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
    fun provideAddPostFavoriteUseCase(repository: PostRepository): AddPostFavoriteUseCase {
        return AddPostFavoriteUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideQueryDao(database: Database): PostDao {
        return database.postDao()
    }

    @Singleton
    @Provides
    fun provideCacheDataSource(dao: PostDao): CacheDataSource<Post> {
        return CacheDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideCloudDataSource(client: RemoteApi, dao: PostDao): CloudDataSource<Post> {
        return CloudDataSourcePostImpl(client, dao)
    }

    @Singleton
    @Provides
    fun providePostsRepository(
            cacheDataSource: CacheDataSource<Post>,
            cloudDataSource: CloudDataSource<Post>
    ): PostRepository {
        return PostRepositoryImpl(cacheDataSource, cloudDataSource)
    }
}