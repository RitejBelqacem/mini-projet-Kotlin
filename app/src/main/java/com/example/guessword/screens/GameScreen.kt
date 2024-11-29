package com.example.guessword.screens

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessword.data.dataset
import kotlinx.coroutines.delay
import com.example.guessword.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    dataset: dataset,
    sharedPreferences: SharedPreferences,
    topScore: Int,
    onTopScoreChange: (Int) -> Unit,
    onBackPress: () -> Unit
) {
    val examples = dataset.getAnglaisExamples()

    // State variables
    var currentExampleIndex by rememberSaveable { mutableStateOf(0) }
    var score by rememberSaveable { mutableStateOf(0) }
    var currentGuess by rememberSaveable { mutableStateOf("") }
    var showResultImage by rememberSaveable { mutableStateOf(false) }
    var resultImageRes by rememberSaveable { mutableStateOf(0) }
    var resultMessage by rememberSaveable { mutableStateOf("") }
    var incorrectMessage by rememberSaveable { mutableStateOf("") }
    var attempts by rememberSaveable { mutableStateOf(0) }
    var timeLeft by rememberSaveable { mutableStateOf(60) }
    var isPaused by remember { mutableStateOf(false) }

    val currentExample = examples[currentExampleIndex]
    val isTimeUp by remember { derivedStateOf { timeLeft <= 0 } }

    // Timer logic
    LaunchedEffect(timeLeft, isPaused) {
        if (timeLeft > 0 && !isPaused) {
            delay(1000L)
            timeLeft--
        }
    }

    // Automatic result handling
    LaunchedEffect(showResultImage) {
        if (showResultImage) {
            isPaused = true
            delay(3000L)
            currentExampleIndex = (currentExampleIndex + 1) % examples.size
            resultMessage = ""
            resultImageRes = 0
            showResultImage = false
            attempts = 0
            incorrectMessage = "" // Clear incorrect message
            isPaused = false
        }
    }

    if (isTimeUp) {
        if (score > topScore) {
            onTopScoreChange(score)
        }
        AlertDialog(
            onDismissRequest = { onBackPress() },
            confirmButton = {
                Button(onClick = { onBackPress() }) {
                    Text("Back to Home")
                }
            },
            title = { Text("Time's up!") },
            text = { Text("Your final score is $score.") }
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF9F9F9),
                            Color(0xFFECECEC)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Centered Timer
                Text(
                    text = "Time Left: $timeLeft s",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE53935),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .background(
                            Color(0xFFFFEBEE),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Score Card
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Score: $score",
                            fontSize = 20.sp,
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Top Score: $topScore",
                            fontSize = 20.sp,
                            color = Color(0xFFFFA726),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (showResultImage) {
                    // Result Display
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .border(4.dp, Color(0xFFBBDEFB), RoundedCornerShape(24.dp))
                                .background(Color(0xFFF0F8FF), RoundedCornerShape(24.dp))
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = resultImageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(8.dp)
                            )
                        }
                        Text(
                            text = currentExample.correctAnswer,
                            fontSize = 28.sp,
                            color = Color(0xFF388E3C),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = resultMessage,
                            fontSize = 20.sp,
                            color = if (resultMessage.contains("Correct")) Color(0xFF388E3C) else Color(0xFFD32F2F),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    // Images
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        currentExample.images.forEach { image ->
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(8.dp)
                                    .border(4.dp, Color(0xFFBBDEFB), RoundedCornerShape(16.dp))
                                    .background(Color.White, RoundedCornerShape(16.dp))
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Input Field
                    TextField(
                        value = currentGuess,
                        onValueChange = { currentGuess = it },
                        label = { Text("Your Guess") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF9F9F9),
                            focusedIndicatorColor = Color(0xFFFF9800),
                            unfocusedIndicatorColor = Color(0xFFBDBDBD)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Incorrect Message
                    if (incorrectMessage.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = incorrectMessage,
                            fontSize = 18.sp,
                            color = Color.Red,
                            modifier = Modifier.padding(vertical = 4.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Submit Button
                    Button(
                        onClick = {
                            if (currentGuess.equals(currentExample.correctAnswer, ignoreCase = true)) {
                                score += 10
                                resultMessage = "Correct!"
                                resultImageRes = currentExample.correctImage
                                showResultImage = true
                            } else {
                                attempts++
                                incorrectMessage = "Incorrect! Try again."
                                if (attempts >= 2) {
                                    resultMessage = "Incorrect! Moving to the next question."
                                    resultImageRes = currentExample.correctImage
                                    score = maxOf(0, score - 10)
                                    showResultImage = true
                                }
                            }
                            currentGuess = ""
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 4.dp,
                                color = Color(0xFFBBDEFB),
                                shape = RoundedCornerShape(50)
                            ),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text("Submit", color = Color(0xFF000000), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
