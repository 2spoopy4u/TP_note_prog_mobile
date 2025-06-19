package com.example.tp_flashcard.flashcards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlashCardViewModel(private val flashCardRepository: FlashcardRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(FlashcardUiState());
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow();

    fun getFlashCards(categoryId: Int){
        _uiState.value = _uiState.value.copy(
            cardList = flashCardRepository.getFlashCardByCategoryStream(categoryId),
        )
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
                currentId = newId,
            )
        }
    }
    fun reset(categoryId: Int) {
        val cards = flashCardRepository.getFlashCardByCategoryStream(categoryId)
        _uiState.value = FlashcardUiState(
            cardList = cards,
            currentId = 0,
            isSessionFinished = false
        )
    }
    init {

    }
}
