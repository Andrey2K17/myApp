package com.pg13.myapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pg13.myapp.data.entities.local.PostLocal

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    suspend fun getAll(): List<PostLocal>
    //fun getAll(): Flow<List<PostLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePost(query: PostLocal)

    @Delete
    suspend fun removePost(post: PostLocal)

    @Update
    suspend fun updateUsers(vararg post: PostLocal)
}