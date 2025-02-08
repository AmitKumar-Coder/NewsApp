package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.remote.NewsApiService
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Dagger Hilt Module for providing dependencies
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provides Retrofit API Service
    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    // Provides Database for storing news data
    @Provides
    @Singleton
    fun provideDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news_database"
        ).build()
    }

    // Provides DAO for interacting with the database
    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }

    // Provides Repository for managing news data
    @Provides
    @Singleton
    fun provideNewsRepository(apiService: NewsApiService, newsDao: NewsDao): NewsRepository {
        return NewsRepository(apiService, newsDao)
    }
}