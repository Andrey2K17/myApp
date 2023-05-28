package com.pg13.myapp.data.mappers

import com.pg13.myapp.data.entities.local.PostLocal
import com.pg13.myapp.data.entities.remote.AlbumRemote
import com.pg13.myapp.data.entities.remote.CommentRemote
import com.pg13.myapp.data.entities.remote.PhotoRemote
import com.pg13.myapp.data.entities.remote.PostRemote
import com.pg13.myapp.domain.entites.Album
import com.pg13.myapp.domain.entites.Comment
import com.pg13.myapp.domain.entites.Photo
import com.pg13.myapp.domain.entites.Post

fun AlbumRemote.mapToDomain(): Album = Album(userId, id, title)

fun PhotoRemote.mapToDomain(): Photo = Photo(albumId, id, title, url, thumbnailUrl)

fun PostLocal.mapToDomain(): Post = Post(userId, id, title, body, favorite)

fun PostRemote.mapToDomain(): Post = Post(userId, id, title, body)

fun CommentRemote.mapToDomain(): Comment = Comment(postId, id, name, email, body)

