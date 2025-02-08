package com.example.newsapp.utils

import com.example.newsapp.data.remote.Article

// Resource class to handle API response
sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>() // Success state with data
    data class Error<T>(val message: String) : Resource<T>() // Error state with message
    class Loading<T> : Resource<T>() // Loading state
}