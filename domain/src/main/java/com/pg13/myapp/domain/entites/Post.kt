package com.pg13.myapp.domain.entites

data class Post(val userId: Int, val id: Int, val title: String, val body: String, var favorite: Boolean = false)