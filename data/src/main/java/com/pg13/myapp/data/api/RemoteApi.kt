package com.pg13.myapp.data.api

import com.pg13.myapp.data.entities.remote.PostRemote
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostRemote>>
}