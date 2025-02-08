package com.example.newsapp.utils

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Composable
fun LottieAnimation(
    rawRes: Int,  // Lottie JSON resource (e.g., R.raw.loading)
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(rawRes))
    val progress by animateLottieCompositionAsState(composition)

    // Correctly Call the Lottie Library Function
    LottieAnimation(
        composition = composition,
        progress = { progress },  // Provides animation progress
        modifier = modifier.size(250.dp)  // Sets the size of the animation
    )
}
