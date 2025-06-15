package com.example.tp_flashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
    val cardText = if (showAnswer.value) {
        state.cardList[state.currentId].answer
    } else {
        state.cardList[state.currentId].question
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Flashcard App " + state.currentId,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = mediumPadding)
        )
        Row {
            FlashCard(
                modifier = Modifier,
                cardText = cardText,
                changeShowState = { showAnswer.value = !showAnswer.value }
            )
            Button(  modifier = Modifier,
                onClick = flashCardViewModel::nextCard,
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
            ) {
                Text(
                    text = stringResource(R.string.next),
                    fontSize = 16.sp
                )
            }
        }
        }
    }


@Composable
fun FlashCard(
    modifier: Modifier = Modifier,
    cardText: String,
    changeShowState : ()->Unit
) {

    Card (
        modifier = Modifier,
        onClick = changeShowState
    ) {
        Text(
            text = cardText,
            fontSize = 16.sp
        )
    }
}