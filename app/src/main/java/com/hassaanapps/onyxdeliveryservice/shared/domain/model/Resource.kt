package com.hassaanapps.onyxdeliveryservice.shared.domain.model

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Loading<T>(val isLoading: Boolean) : Resource<T>()
    data class Error<T>(val error: Throwable) : Resource<T>()
}