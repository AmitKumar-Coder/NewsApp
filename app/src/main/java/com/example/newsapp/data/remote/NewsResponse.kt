package com.example.newsapp.data.remote

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)