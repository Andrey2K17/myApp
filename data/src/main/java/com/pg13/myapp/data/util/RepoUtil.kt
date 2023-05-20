package com.pg13.myapp.data.util

import com.pg13.myapp.domain.entites.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

internal inline fun <ResponseType, ResultType> networkBoundResource(
    crossinline remoteCall: suspend () -> Response<ResponseType>,
    crossinline mapper: (ResponseType) -> ResultType,
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading())

    val result = remoteCall.invoke()
        try {
            when (result.code()) {
                200 -> emit(Resource.Success(mapper(result.body()!!)))
                else -> emit(Resource.Error(exception = HttpException(result)))
            }
        } catch (e: Exception) {
            emit(Resource.Error(exception = e))
        }
}

internal inline fun <ResponseType, LocalType, ResultType> networkBoundResource(
    crossinline remoteCall: suspend () -> Response<ResponseType>,
    crossinline loadLocalData: suspend () -> LocalType?,
    crossinline mapper: (ResponseType, LocalType) -> ResultType,
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading())

    // Сначала загружаем локальные данные
    val localData: LocalType? = loadLocalData()

    val result = remoteCall.invoke()

    try {
        when (result.code()) {
            200 -> emit(Resource.Success(mapper(result.body()!!, localData!!)))
            else -> emit(Resource.Error(exception = HttpException(result)))
        }
    } catch (e: Exception) {
        emit(Resource.Error(exception = e))
    }
}