package com.example.tp_flashcard.flashcards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val flashCardRepository: FlashcardRepository): ViewModel() {
    fun getCategories(): List<FlashCardCategory> {
        return flashCardRepository.getAllFlashCardCategoriesStream();
    }

    init {

    }
}