package com.example.tp_flashcard.flashcards

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlashCard (
    // Unique identifier for the card
    // Identifier for the category this card belongs to
    // Text of the question shown to the user
    // Text of the answer revealed after flipping the card
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "categoryId") val categoryId: Int,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "answer") val answer: String
)

@Entity
// Represents a category grouping multiple flashcards
data class FlashCardCategory (
    // Unique identifier for the category
    // Display name of the category
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String
)

