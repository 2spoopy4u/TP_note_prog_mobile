package com.example.tp_flashcard.flashcards

data class FlashCard (
    // Unique identifier for the card
    // Identifier for the category this card belongs to
    // Text of the question shown to the user
    // Text of the answer revealed after flipping the card
    val id: Int,
    val categoryId: Int,
    val question: String,
    val answer: String
)

// Represents a category grouping multiple flashcards
data class FlashCardCategory (
    // Unique identifier for the category
    // Display name of the category
    val id: Int,
    val name: String
)