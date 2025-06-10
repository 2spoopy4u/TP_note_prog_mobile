package com.example.tp_flashcard.flashcards

object FlashcardRepository {
    // List of categories available in the app
    val categories : List < FlashCardCategory > = listOf (
        // ...
        FlashCardCategory(id = 1, name = "Math"),
        FlashCardCategory(id = 2, name = "Science"),
        FlashCardCategory(id = 3, name = "History"),
        FlashCardCategory(id = 4, name = "Literature"),
    )
    // List of flashcards with references to categories
    val flashcards : List < FlashCard > = listOf (
        // ...
        FlashCard(id = 1, categoryId = 1, question = "What is 2 + 2?", answer = "4"),
        FlashCard(id = 2, categoryId = 1, question = "What is the square root of 16?", answer = "4"),
        FlashCard(id = 3, categoryId = 2, question = "What is H2O?", answer = "Water"),
        FlashCard(id = 4, categoryId = 2, question = "What planet is known as the Red Planet?", answer = "Mars"),
        FlashCard(id = 5, categoryId = 3, question = "Who was the first President of the United States?", answer = "George Washington"),
        FlashCard(id = 6, categoryId = 3, question = "What year did World War II end?", answer = "1945"),
        FlashCard(id = 7, categoryId = 4, question = "Who wrote 'Romeo and Juliet'?", answer = "William Shakespeare"),
        FlashCard(id = 8, categoryId = 4, question = "What is the main theme of 'To Kill a Mockingbird'?", answer = "Racial injustice"),
    )
}