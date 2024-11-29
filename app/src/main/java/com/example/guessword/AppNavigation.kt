package com.example.guessword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guessword.screens.HomeScreen
import com.example.guessword.screens.HowToPlay
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Track top score using remember state
    var topScore by remember { mutableStateOf(0) }

    // Function to update top score
    val onTopScoreChange: (Int) -> Unit = { newScore ->
        topScore = newScore
    }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onPlayClick = { /* Navigate to the Game Screen */ },
                onResetClick = {
                    topScore = 0 // Reset the top score
                },
                topScore = topScore, // Pass the current top score
                onHowToPlayClick = { navController.navigate("how_to_play") },
                onTopScoreChange = onTopScoreChange // Pass the function to update top score
            )
        }
        composable("how_to_play") {
            HowToPlay(onBackPress = { navController.popBackStack() })
        }
    }
}
