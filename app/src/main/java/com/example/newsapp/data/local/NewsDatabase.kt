package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.remote.Article

//Define Room Database for News App
@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)  // for customs type for converters
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao //Provide access to the dao for database operations
}