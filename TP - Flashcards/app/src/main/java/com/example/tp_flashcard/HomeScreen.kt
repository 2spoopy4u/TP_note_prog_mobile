package com.example.tp_flashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.example.tp_flashcard.flashcards.FlashCardCategory
import com.example.tp_flashcard.flashcards.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)

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
            text = "Flashcard App",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = mediumPadding)
        )
        homeViewModel.getCategories().forEach { category ->
            CategoryItem(
                modifier = Modifier,
                category = category
            )
        }
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: FlashCardCategory,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
    ) {
        Text(
            text = category.name,
            fontSize = 16.sp
        )
    }
}