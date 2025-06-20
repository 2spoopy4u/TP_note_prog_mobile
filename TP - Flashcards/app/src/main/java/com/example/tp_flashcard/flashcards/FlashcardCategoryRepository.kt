package com.example.tp_flashcard.flashcards

import kotlinx.coroutines.flow.Flow

class FlashcardCategoryRepository(private val flashCardCategoryDAO: FlashCardCategoryDAO) {


    suspend fun getAllFlashCardCategoryStream(): Flow<List<FlashCardCategory?>> {
        return flashCardCategoryDAO.getAllFlashCardCategory()
    }

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getFlashCardCategoryStream(id: Int): Flow<FlashCardCategory?> {
        return flashCardCategoryDAO.getFlashCardCategory(id)
    }

    /**
     * Insert item in the data source
     */
    suspend fun insertFlashCardCategory(item: FlashCardCategory) {
        flashCardCategoryDAO.insert(item)
    }

    /**
     * Delete item from the data source
     */
    suspend fun deleteFlashCardCategory(item: FlashCardCategory) {
        flashCardCategoryDAO.delete(item)
    }

    /**
     * Update item in the data source
     */
    suspend fun updateFlashCardCategory(item: FlashCardCategory) {
        flashCardCategoryDAO.update(item)
    }



}