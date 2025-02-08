package com.example.newsapp.ui.navigation

// Use sealed class for better navigation structure
sealed class Screen(val route: String) {
    object NewsList : Screen("news_list") // Route for news list screen
    object NewsDetail : Screen("news_detail") // Route for news detail screen
}
