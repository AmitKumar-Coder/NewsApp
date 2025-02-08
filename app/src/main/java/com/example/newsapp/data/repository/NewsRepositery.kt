package com.example.newsapp.data.repository

import android.util.Log
import com.example.newsapp.data.remote.Article
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.NewsApiService
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//Repository for manages news data
class NewsRepository @Inject constructor(
    private val apiService: NewsApiService,
    private val newsDao: NewsDao
) {
    // Fetch news from API & store in local database
    fun searchNews(query: String, from: String, sortBy: String, apiKey: String): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading()) // Show loading state

        try {
            val response = apiService.searchNews(query, from, sortBy, apiKey)
            newsDao.clearArticles() // delete old article from table
            newsDao.insertArticles(response.articles) // store new articles in table
            emit(Resource.Success(response.articles))
        } catch (e: Exception) {
            Log.e("NewsRepository", "API Fetch Failed: ${e.message}")

            //Fetch offline data when API call failed
            val cachedArticles = newsDao.getArticles()
            if (cachedArticles.isNotEmpty()) {
                emit(Resource.Success(cachedArticles)) // Return offline data
            } else {
                emit(Resource.Error("No internet and no saved data available.")) //Show error message
            }
        }
    }
}