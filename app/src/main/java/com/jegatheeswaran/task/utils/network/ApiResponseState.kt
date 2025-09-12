package com.jegatheeswaran.task.utils.network

sealed class ApiResponseState<out R> {
    data class Success<out T>(val data: T) : ApiResponseState<T>()
    data class Error(val exception: Exception) : ApiResponseState<Nothing>()
    data object Loading : ApiResponseState<Nothing>()
}