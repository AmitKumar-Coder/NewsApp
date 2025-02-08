package com.example.newsapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.ui.screens.NewsDetailScreen
import com.example.newsapp.ui.screens.NewsListScreen

// Navigation Graph
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.NewsList.route) {

        // News List Screen
        composable(Screen.NewsList.route) {
            NewsListScreen(navController)
        }

        // News Detail Screen (Pass URL, Title, and Description)
        composable(
            route = Screen.NewsDetail.route + "/{title}/{url}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("url") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val imageUrl = backStackEntry.arguments?.getString("url") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""

            NewsDetailScreen(title, imageUrl, description) // Pass all arguments
        }
    }
}

