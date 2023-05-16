package com.pg13.myapp.domain.entites

sealed class Resource<out T>(open val data: T?) {

    class Success<T>(override val data: T) : Resource<T>(data)

    class Loading<T>(data: T? = null) : Resource<T>(data)

    class Error<T>(
        data: T? = null,
        val exception: Exception? = null
    ) : Resource<T>(data)
}