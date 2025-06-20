package com.example.tp_flashcard.flashcards

import kotlinx.coroutines.flow.Flow

class FlashcardRepository(private val flashCardDAO: FlashCardDAO) {

    suspend fun getFlashCardByCategoryStream(categoryId: Int): Flow<List<FlashCard>> {
        return flashCardDAO.getFlashCardByCategory(categoryId)
    }


    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getFlashCardStream(id: Int): Flow<FlashCard?> {
        return flashCardDAO.getFlashCard(id)
    }

    /**
     * Insert item in the data source
     */
    suspend fun insertFlashCard(item: FlashCard) {
        flashCardDAO.insertFlashCard(item)
    }

    /**
     * Delete item from the data source
     */
    suspend fun deleteFlashCard(item: FlashCard) {
        flashCardDAO.deleteFlashCard(item)
    }

    /**
     * Update item in the data source
     */
    suspend fun updateFlashCard(item: FlashCard) {
        flashCardDAO.updateFlashCard(item)
    }



}