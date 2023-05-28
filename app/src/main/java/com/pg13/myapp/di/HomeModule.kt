package com.pg13.myapp.di

import com.pg13.myapp.data.api.RemoteApi
import com.pg13.myapp.data.net.CloudDataSource
import com.pg13.myapp.data.net.home.CloudDataSourceHomeImpl
import com.pg13.myapp.data.repositories.HomeRepositoryImpl
import com.pg13.myapp.domain.entites.ItemHome
import com.pg13.myapp.domain.repositories.HomeRepository
import com.pg13.myapp.domain.usecases.GetHomeItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
    @Singleton
    @Provides
    fun provideGetHomeItemUseCase(repository: HomeRepository) : GetHomeItemsUseCase {
        return GetHomeItemsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(cloudDataSource: CloudDataSource<ItemHome>): HomeRepository {
        return HomeRepositoryImpl(cloudDataSource)
    }

    @Singleton
    @Provides
    fun provideSourceCloudDataSourceHome(client: RemoteApi): CloudDataSource<ItemHome> {
        return CloudDataSourceHomeImpl(client)
    }
}