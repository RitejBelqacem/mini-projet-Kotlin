package com.example.guessword

import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.guessword.screens.GameScreen
import com.example.guessword.screens.HomeScreen
import com.example.guessword.data.dataset
import com.example.guessword.screens.HowToPlay

@Composable
fun GuessWordApp(
    languageDataset: dataset, // Use dataset for the game
    sharedPreferences: SharedPreferences
) {
    var currentScreen by rememberSaveable { mutableStateOf("home") }
    var topScore by rememberSaveable {
        mutableStateOf(sharedPreferences.getInt("TOP_SCORE", 0))
    }

    // Callback to update the top score
    val onTopScoreChange: (Int) -> Unit = { newScore ->
        topScore = newScore
        sharedPreferences.edit().putInt("TOP_SCORE", newScore).apply()
    }

    when (currentScreen) {
        "home" -> HomeScreen(
            onPlayClick = { currentScreen = "game" },
            onResetClick = {
                sharedPreferences.edit().putInt("TOP_SCORE", 0).apply()
                topScore = 0
            },
            topScore = topScore,
            onHowToPlayClick = { currentScreen = "how_to_play" },
            onTopScoreChange = onTopScoreChange
        )
        "game" -> GameScreen(
            dataset = languageDataset,
            sharedPreferences = sharedPreferences,
            topScore = topScore,
            onBackPress = { currentScreen = "home" },
            onTopScoreChange = onTopScoreChange // Pass the callback here
        )
        "how_to_play" -> HowToPlay(
            onBackPress = { currentScreen = "home" }
        )
    }
}
