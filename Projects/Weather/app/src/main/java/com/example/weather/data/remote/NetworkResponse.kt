package com.example.weather.data.remote

sealed class NetworkResponse<out T> {
    data class success<out T>(val data:T) : NetworkResponse<T>()
    data class Error(val message: String) : NetworkResponse<Nothing>()
    object Loading : NetworkResponse<Nothing>()


}