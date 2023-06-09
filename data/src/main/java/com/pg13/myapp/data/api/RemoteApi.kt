package com.pg13.myapp.data.api

import com.pg13.myapp.data.entities.remote.AlbumRemote
import com.pg13.myapp.data.entities.remote.CommentRemote
import com.pg13.myapp.data.entities.remote.PhotoRemote
import com.pg13.myapp.data.entities.remote.PostRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {
    @GET("posts")
    suspend fun getPosts(): Response<List<PostRemote>>

    @GET("comments")
    suspend fun getCommentsByPostId(
        @Query("postId") postId: Int
    ): Response<List<CommentRemote>>

    @GET("albums")
    suspend fun getAlbums(): Response<List<AlbumRemote>>

    @GET("photos")
    suspend fun getPhotos(): Response<List<PhotoRemote>>
}