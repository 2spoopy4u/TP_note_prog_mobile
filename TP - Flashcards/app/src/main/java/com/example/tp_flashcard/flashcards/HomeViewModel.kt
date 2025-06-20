package com.example.tp_flashcard.flashcards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModelFactory(private val repository: FlashcardCategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class HomeViewModel(private val flashcardCategoryRepository: FlashcardCategoryRepository): ViewModel() {
    private val _categories = MutableStateFlow<List<FlashCardCategory?>>(emptyList())
    val categories: StateFlow<List<FlashCardCategory?>> = _categories

    init {
        viewModelScope.launch {
            flashcardCategoryRepository.getAllFlashCardCategoryStream()
                .collect { list ->
                    _categories.value = list
                }
        }
    }
}