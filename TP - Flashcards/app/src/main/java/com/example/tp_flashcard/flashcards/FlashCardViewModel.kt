package com.example.tp_flashcard.flashcards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlashCardViewModel(private val categoryId: Int):ViewModel() {
    private val _uiState = MutableStateFlow(FlashcardUiState());
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow();

    fun getFlashCards(): List<FlashCard> {
        return FlashcardRepository.flashcards;
    }

    init {

    }
}
