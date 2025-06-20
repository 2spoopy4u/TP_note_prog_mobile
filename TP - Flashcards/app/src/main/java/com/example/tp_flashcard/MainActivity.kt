package com.example.tp_flashcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.tp_flashcard.flashcards.FlashCardDatabase
import com.example.tp_flashcard.flashcards.FlashCardViewModel
import com.example.tp_flashcard.flashcards.FlashCardViewModelFactory
import com.example.tp_flashcard.flashcards.FlashcardCategoryRepository
import com.example.tp_flashcard.flashcards.FlashcardRepository
import com.example.tp_flashcard.flashcards.HomeViewModel
import com.example.tp_flashcard.flashcards.HomeViewModelFactory
import com.example.tp_flashcard.ui.theme.TP_FlashcardTheme

class MainActivity : ComponentActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var flashCardViewModel: FlashCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = FlashCardDatabase.getDatabase(this)

        val homeRepository = FlashcardCategoryRepository(database.flashCardCategoryDao())
        val flashcardRepository = FlashcardRepository(database.flashCardDao())

        homeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(homeRepository)
        )[HomeViewModel::class.java]

        flashCardViewModel = ViewModelProvider(
            this,
            FlashCardViewModelFactory(flashcardRepository)
        )[FlashCardViewModel::class.java]

        setContent {
            TP_FlashcardTheme {
                AppNavHost(
                    homeViewModel = homeViewModel,
                    flashCardViewModel = flashCardViewModel
                )
            }
        }
    }
}