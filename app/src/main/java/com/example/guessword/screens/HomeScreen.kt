package com.example.guessword.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessword.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPlayClick: () -> Unit,
    onResetClick: () -> Unit,
    topScore: Int,
    onHowToPlayClick: () -> Unit,
    onTopScoreChange: (Int) -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Image d'arrière-plan
                Image(
                    painter = painterResource(id = R.drawable.fond),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Contenu principal avec transparence
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Black.copy(alpha = 0.7f)
                                )
                            )
                        )
                )

                // Contenu principal
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Bienvenue
                    Text(
                        text = "Welcome!",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Carte "Top Score"
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent), // Fond transparent
                        elevation = CardDefaults.cardElevation(0.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .border(
                                width = 2.dp,
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFFFD700), // Or
                                        Color(0xFFFFA500), // Orange vif
                                        Color(0xFFFF4500)  // Rouge orangé
                                    )
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Top Score Icon",
                                    tint = Color(0xFFFFD700), // Couleur dorée
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Top Score",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                // Utilisation de GradientText pour afficher le score avec un dégradé
                                GradientText(
                                    text = "$topScore",
                                    gradient = Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFFFFD700), // Doré
                                            Color(0xFFFFA500)  // Orange vif
                                        )
                                    ),
                                    fontSize = 48,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Bouton "Play"
                    Button(
                        onClick = onPlayClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .border(2.dp, Color.White, RoundedCornerShape(50)), // Contour blanc
                        shape = RoundedCornerShape(50), // Bouton arrondi
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent, // Fond transparent
                            contentColor = Color.White // Texte blanc
                        )
                    ) {
                        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Play")
                    }

                    // Bouton "How To Play"
                    Button(
                        onClick = onHowToPlayClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .border(2.dp, Color.White, RoundedCornerShape(50)), // Contour blanc
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent, // Fond transparent
                            contentColor = Color.White
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "How To Play")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("How To Play")
                    }

                    // Bouton "Reset"
                    Button(
                        onClick = onResetClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .border(2.dp, MaterialTheme.colorScheme.error, RoundedCornerShape(50)), // Contour rouge
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent, // Fond transparent
                            contentColor = MaterialTheme.colorScheme.error // Texte rouge
                        )
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reset")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Reset Game")
                    }
                }
            }
        }
    )
}

@Composable
fun GradientText(
    text: String,
    gradient: Brush,
    fontSize: Int,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                brush = gradient,
                fontSize = fontSize.sp,
                fontWeight = fontWeight
            )
        )
    }
}

