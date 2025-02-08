package com.example.newsapp.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

//Define Article Table
@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, //Unique id for article table
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

//Define Source Table
data class Source(
    val id: String?,
    val name: String
)
