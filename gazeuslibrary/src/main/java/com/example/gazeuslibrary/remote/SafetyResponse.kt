package com.example.gazeuslibrary.remote

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class SafetyResponse<out T> {
    data class Success<out T>(val value: T) : SafetyResponse<T>()
    data class GenericError(val code: Int? = null, val error: Response<*>? = null) : SafetyResponse<Nothing>()
    object NetworkError : SafetyResponse<Nothing>()
}

suspend fun <T> safeRequest(request: suspend () -> T): SafetyResponse<T> {
    return try {
        SafetyResponse.Success(request())
    } catch (throwable: Throwable) {
        return when (throwable) {
            is IOException -> {
                SafetyResponse.NetworkError
            }
            is HttpException -> {
                SafetyResponse.GenericError(throwable.code(), throwable.response())
            }
            else -> {
                SafetyResponse.GenericError(null, null)
            }
        }
    }
}