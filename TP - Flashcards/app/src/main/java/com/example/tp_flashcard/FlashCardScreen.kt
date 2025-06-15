package com.example.tp_flashcard

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp_flashcard.flashcards.FlashCardViewModel

@Composable
fun FlashCardScreen(
    flashCardViewModel: FlashCardViewModel,
    categoryId: Int,
    navToHomeScreen: () -> Unit
) {
    val state by flashCardViewModel.uiState.collectAsState()

    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val showAnswer = remember { mutableStateOf(false) }
    LaunchedEffect (key1 =categoryId)
    {
        flashCardViewModel.reset(categoryId)
    }
    LaunchedEffect(state.currentId) {
        showAnswer.value = false
    }
    if (state.isSessionFinished) {
        // Navigue vers "home" et retire la route actuelle de la backstack
        navToHomeScreen()
        return // pour ne pas afficher les composants inutiles
    }
    if (state.cardList.isEmpty()) {
        // Affiche un loader ou rien en attendant
        Card(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(mediumPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LinearProgressIndicator(
            // progress doit être une fonction lambda car sinon c'est déprécié
            progress = {
                (state.currentId + 1) / state.cardList.size.toFloat()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )

        FlashCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            frontText = state.cardList[state.currentId].question,
            backText = state.cardList[state.currentId].answer,
            isShowingAnswer = showAnswer.value,
            onCardClick = { showAnswer.value = !showAnswer.value }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = flashCardViewModel::nextCard,
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
            ) {
                Text("Je l'ignorai 👎")
            }
            Button(
                onClick = flashCardViewModel::nextCard,
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
            ) {
                Text("Je le savais ! 👍")
            }
        }
    }
}


@Composable
fun FlashCard(
    modifier: Modifier = Modifier,
    frontText: String,
    backText: String,
    isShowingAnswer: Boolean,
    onCardClick: () -> Unit
) {
    // Animate rotation for flip effect
    val animatedRotationY by animateFloatAsState(
        targetValue = if (isShowingAnswer) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = ""
    )


    val isFront = animatedRotationY <= 90f

    val backgroundColor = if (isFront) Color(0xFF2D9CDB) else Color(0xFF56CCF2) // Back is lighter

    Card(
        modifier = modifier
            .graphicsLayer {
                rotationY = animatedRotationY
                cameraDistance = 12 * density
            }
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onCardClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isFront) {
                Text(
                    text = frontText,
                    fontSize = 20.sp,
                    color = Color.White
                )
            } else {
                // Le fait de retourner la flashcard retourne le texte avec
                // du coup on applique une rotation au texte pour qu'il soit lisible
                Text(
                    text = backText,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.graphicsLayer {
                        rotationY = 180f
                    }
                )
            }
        }
    }
}
