package com.example.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.remote.Article
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// NewsViewModel
@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    private val _newsState = MutableStateFlow<Resource<List<Article>>>(Resource.Loading()) // Mutable state flow for news
    val newsState: StateFlow<Resource<List<Article>>> = _newsState // Immutable state flow for news

    // Fetch news from repository
    fun fetchNews(query: String, from: String, sortBy: String, apiKey: String) {
        // launch a coroutine in the ViewModel scope
        viewModelScope.launch {
            repository.searchNews(query, from, sortBy, apiKey).collect { resource ->
                _newsState.value = resource // Update the news state flow
            }
        }
    }
}