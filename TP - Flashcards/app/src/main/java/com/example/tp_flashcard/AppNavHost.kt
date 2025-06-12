package com.example.tp_flashcard

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.flashcards.FlashCardCategory
import com.example.tp_flashcard.flashcards.FlashCardViewModel
import com.example.tp_flashcard.flashcards.HomeViewModel

@Composable
fun AppNavHost ( homeViewModel : HomeViewModel, flashCardViewModel:FlashCardViewModel) {
    val navController = rememberNavController ()
    fun navToFlashScreen(category: FlashCardCategory){
        navController.navigate("flashCard/${category.id}"){
        }
    }
    NavHost ( navController , startDestination = " home ") {
        composable (" home ") {
            HomeScreen ( homeViewModel = homeViewModel, ::navToFlashScreen)
        }
        composable("flashCard/{categoryId}"){ backStackEntry->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            if (categoryId != null) {
                FlashCardScreen( flashCardViewModel , categoryId = categoryId.toInt())
            }
        }


    }
}