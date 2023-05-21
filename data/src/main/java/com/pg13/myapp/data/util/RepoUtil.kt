package com.pg13.myapp.data.util

import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal inline fun <ResponseType, ResultType> networkBoundResource(
    crossinline remoteCall: suspend () -> Response<ResponseType>,
    crossinline mapper: (ResponseType) -> ResultType,
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading())

    val result = remoteCall.invoke()
    when (result.code()) {
        200 -> emit(Resource.Success(mapper(result.body()!!)))
        else -> emit(Resource.Error(exception = HttpException(result)))
    }
}.catchWithHandle()

internal inline fun <ResponseType, LocalType, ResultType> networkBoundResource(
    crossinline remoteCall: suspend () -> Response<ResponseType>,
    crossinline loadLocalData: suspend () -> LocalType?,
    crossinline mapper: (ResponseType, LocalType) -> ResultType,
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading())

    val localData: LocalType? = loadLocalData()
    val result = remoteCall.invoke()
    when (result.code()) {
        200 -> emit(Resource.Success(mapper(result.body()!!, localData!!)))
        else -> emit(Resource.Error(exception = HttpException(result)))
    }
}.catchWithHandle()


fun <T> Flow<Resource<T>>.catchWithHandle(): Flow<Resource<T>> = catch { exception ->
    if (exception is IOException) {
        emit(Resource.Error(exception = exception, message = "Ошибка доступа в интернет"))
    } else {
        emit(
            Resource.Error(
                exception = exception,
                message = "Неизвестная ошибка\n${exception.message}"
            )
        ) // неизвестная ошибка
    }
}