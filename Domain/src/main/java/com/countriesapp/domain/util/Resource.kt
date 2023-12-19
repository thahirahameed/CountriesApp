package com.countriesapp.domain.util

sealed class Resource<T>{
    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val message:String): Resource<T>()
    class Loading<T>(val isLoading: Boolean = false): Resource<T>()

}
