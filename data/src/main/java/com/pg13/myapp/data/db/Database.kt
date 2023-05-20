package com.pg13.myapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pg13.myapp.data.entities.local.PostLocal

@Database(entities = [PostLocal::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun postDao(): PostDao
}