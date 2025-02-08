package com.example.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

// Defines Retrofit API Service for fetching news articles
interface NewsApiService {
    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q") query: String, // Query parameter for search query
        @Query("from") from: String, // Query parameter for start date
        @Query("sortBy") sortBy: String, // Query parameter for sorting order
        @Query("apiKey") apiKey: String // Query parameter for API key
    ): NewsResponse // Returns a NewsResponse object
}
