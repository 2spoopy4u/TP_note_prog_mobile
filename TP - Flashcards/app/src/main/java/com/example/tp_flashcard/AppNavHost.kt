package com.example.tp_flashcard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tp_flashcard.flashcards.HomeViewModel

@Composable
fun AppNavHost ( homeViewModel : HomeViewModel) {
    val navController = rememberNavController ()
    NavHost ( navController , startDestination = " home ") {
        composable (" home ") {
            HomeScreen ( homeViewModel = homeViewModel /** ... **/)
        }
    }
}