package com.example.tp_flashcard.flashcards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlashCardViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(FlashcardUiState());
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow();

    fun getFlashCards(categoryId: Int): List<FlashCard> {
        return FlashcardRepository.flashcards.filter{it.categoryId == categoryId}
    }
    fun nextCard(){
        val newId = _uiState.value.currentId +1
        if(newId >= _uiState.value.cardList.size){
            _uiState.value = _uiState.value.copy(
                isSessionFinished = true
            )
        }
        else {
            _uiState.value = _uiState.value.copy(
                currentId = newId
            )
        }
    }
    init {

    }
}
