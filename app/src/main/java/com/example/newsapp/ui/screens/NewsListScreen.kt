package com.example.newsapp.ui.screens

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.data.remote.Article
import com.example.newsapp.ui.components.NewsItem
import com.example.newsapp.utils.Constants
import com.example.newsapp.utils.LottieAnimation
import com.example.newsapp.utils.Resource
import com.example.newsapp.viewmodel.NewsViewModel

// News List Screen
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsListScreen(
    navController: NavHostController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val newsState by viewModel.newsState.collectAsState()

    // Fetch news when the screen is first displayed
    LaunchedEffect(Unit) {
        viewModel.fetchNews(
            query = Constants.DEFAULT_QUERY,
            from = Constants.DEFAULT_FROM_DATE,
            sortBy = Constants.DEFAULT_SORT_BY,
            apiKey = Constants.API_KEY
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Display the topbar
                    Text(
                        "News", modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        ),
                        textAlign = TextAlign.Center // Center the title
                    )
                }
            )
        },
        content = { innerPadding ->

            Column {
                when (newsState) {
                    is Resource.Loading -> {
                        // Show Loading Indicator
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                           // CircularProgressIndicator()
                            LottieAnimation(rawRes = R.raw.loading)
                        }
                    }

                    // Handle Success and Error States
                    is Resource.Success -> {
                        LazyColumn(
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            val articles = (newsState as? Resource.Success<List<Article>>)?.data ?: emptyList() // Get the list of articles
                            items(articles) { article ->
                                val encodedTitle = Uri.encode(article.title)
                                val encodedUrl = Uri.encode(article.urlToImage ?: "")
                                val encodedDescription = Uri.encode(article.description ?: "")

                                //navigate to detail screen

                                NewsItem(article) {
                                    navController.navigate("news_detail/$encodedTitle/$encodedUrl/$encodedDescription")
                                }
                            }
                        }
                    }

                    // Handle Error State
                    is Resource.Error -> {
                        val errorMessage = (newsState as? Resource.Error)?.message ?: "Unknown error" // Get the error message

                        // Show Error Message
                        Column (modifier = Modifier.fillMaxSize(),  verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            LottieAnimation(rawRes = R.raw.error)
                            Text(
                                text = errorMessage,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }

        }
    )
}

