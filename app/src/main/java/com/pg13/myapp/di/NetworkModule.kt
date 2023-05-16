package com.pg13.myapp.di

import com.google.gson.GsonBuilder
import com.pg13.myapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private fun configureOkHttpClientBuilder(
        builder: OkHttpClient.Builder
    ) {
        if (BuildConfig.DEBUG) builder.addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
    }

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .also { configureOkHttpClientBuilder(it) }
    }

    /** Creates OkHttpClient with logger and interceptor for applying headers */
    @Singleton
    @Provides
    fun provideDefaultOkHttpClient(
        builder: OkHttpClient.Builder,
    ): OkHttpClient = builder.build()

    private fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideDefaultRetrofitClient(
        okHttpClient: OkHttpClient,
    ): Retrofit = provideRetrofitClient(okHttpClient)
}