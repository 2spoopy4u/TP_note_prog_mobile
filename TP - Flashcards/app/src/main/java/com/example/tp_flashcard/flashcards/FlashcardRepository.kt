package com.example.tp_flashcard.flashcards

import kotlinx.coroutines.flow.Flow

class FlashcardRepository(private val flashCardDAO: FlashCardDAO) {

    // List of flashcards with references to categories
    /*val flashcards : List < FlashCard > = listOf (
        // ...
        FlashCard(id = 1, categoryId = 1, question = "What is 2 + 2?", answer = "4"),
        FlashCard(id = 2, categoryId = 1, question = "What is the square root of 16?", answer = "4"),
        FlashCard(id = 3, categoryId = 2, question = "What is H2O?", answer = "Water"),
        FlashCard(id = 4, categoryId = 2, question = "What planet is known as the Red Planet?", answer = "Mars"),
        FlashCard(id = 5, categoryId = 3, question = "Who was the first President of the United States?", answer = "George Washington"),
        FlashCard(id = 6, categoryId = 3, question = "What year did World War II end?", answer = "1945"),
        FlashCard(id = 7, categoryId = 4, question = "Who wrote 'Romeo and Juliet'?", answer = "William Shakespeare"),
        FlashCard(id = 8, categoryId = 4, question = "What is the main theme of 'To Kill a Mockingbird'?", answer = "Racial injustice"),
    )*/

    fun getFlashCardByCategoryStream(categoryId: Int): List<FlashCard> {
        return flashCardDAO.getFlashCardByCategory(categoryId)
    }

    fun getAllFlashCardsStream(): List<FlashCard> {
        return flashCardDAO.getAllFlashCards()
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

    /*val categories : List < FlashCardCategory > = listOf (
        // ...
        FlashCardCategory(id = 1, name = "Math"),
        FlashCardCategory(id = 2, name = "Science"),
        FlashCardCategory(id = 3, name = "History"),
        FlashCardCategory(id = 4, name = "Literature"),
    )*/

    fun getAllFlashCardCategoriesStream(): List<FlashCardCategory> {
        return flashCardDAO.getAllFlashCardCategories()
    }

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getFlashCardCategoryStream(id: Int): FlashCardCategory? {
        return flashCardDAO.getFlashCardCategory(id)
    }

    /**
     * Insert item in the data source
     */
    suspend fun insertFlashCardCategory(item: FlashCardCategory) {
        flashCardDAO.insertFlashCardCategory(item)
    }

    /**
     * Delete item from the data source
     */
    suspend fun deleteFlashCardCategory(item: FlashCardCategory) {
        flashCardDAO.deleteFlashCardCategory(item)
    }

    /**
     * Update item in the data source
     */
    suspend fun updateFlashCardCategory(item: FlashCardCategory) {
        flashCardDAO.updateFlashCardCategory(item)
    }
}