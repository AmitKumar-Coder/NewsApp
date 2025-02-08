package com.example.newsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.remote.Article

//DAO for managing database operations on "articles" table
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>) // Inserts a list of articles

    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<Article> // fetches all saved articles

    @Query("DELETE FROM articles")
    suspend fun clearArticles() // delete all articles
}