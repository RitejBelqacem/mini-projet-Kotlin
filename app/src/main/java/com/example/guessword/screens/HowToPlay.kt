package com.example.guessword.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HowToPlay(onBackPress: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("How to Play") }) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Here are the rules of the game:")
                Spacer(modifier = Modifier.height(16.dp))
                Text("1. Guess the correct word based on clues.")
                Text("2. You have a limited number of attempts.")
                Text("3. Try to get the highest score!")

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onBackPress, // Go back to the Home screen
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Text("Back to Home")
                }
            }
        }
    )
}
