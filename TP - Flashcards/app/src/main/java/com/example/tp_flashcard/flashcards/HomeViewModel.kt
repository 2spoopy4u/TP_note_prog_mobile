package com.example.tp_flashcard.flashcards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(FlashcardRepository);
    val uiState: StateFlow<FlashcardRepository> = _uiState.asStateFlow();

    fun getCategories(): List<FlashCardCategory> {
        return _uiState.value.categories;
    }

    init {

    }
}