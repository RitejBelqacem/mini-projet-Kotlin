package com.example.guessword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.guessword.data.dataset
import com.example.guessword.ui.theme.GuessWordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val languageDataset = dataset()
        val sharedPreferences = getPreferences(MODE_PRIVATE)

        setContent {
            GuessWordTheme {
                GuessWordApp(
                    languageDataset = languageDataset,
                    sharedPreferences = sharedPreferences
                )
            }
        }
    }
}
