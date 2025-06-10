package com.example.tp_flashcard.flashcards

data class FlashcardUiState (
    val currentId : Int =-1,
    val cardList : List < FlashCard > = listOf(),
    val isSessionFinished : Boolean = false
)