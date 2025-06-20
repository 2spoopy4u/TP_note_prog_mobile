package com.example.tp_flashcard.flashcards

data class FlashcardUiState(
    val currentId: Int = 0,
    val cardList: List<FlashCard> = listOf(),
    val isSessionFinished: Boolean = false,
    val isLoading: Boolean = true
)