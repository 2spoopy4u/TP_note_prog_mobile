package com.example.tp_flashcard.flashcards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FlashCardViewModelFactory(private val repository: FlashcardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlashCardViewModel::class.java)) {
            return FlashCardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class FlashCardViewModel(
    private val flashCardRepository: FlashcardRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FlashcardUiState(isLoading = true))
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow()

    fun reset(categoryId: Int) {
        _uiState.value = FlashcardUiState(
            cardList = emptyList(),
            currentId = 0,
            isSessionFinished = false,
            isLoading = true
        )

        viewModelScope.launch {
            val cards = flashCardRepository.getFlashCardByCategoryStream(categoryId).first()
            _uiState.value = FlashcardUiState(
                cardList = cards,
                currentId = 0,
                isSessionFinished = false,
                isLoading = false
            )
        }
    }

    fun nextCard() {
        val newId = _uiState.value.currentId + 1
        if (newId >= _uiState.value.cardList.size) {
            _uiState.value = _uiState.value.copy(isSessionFinished = true)
        } else {
            _uiState.value = _uiState.value.copy(currentId = newId)
        }
    }
}

