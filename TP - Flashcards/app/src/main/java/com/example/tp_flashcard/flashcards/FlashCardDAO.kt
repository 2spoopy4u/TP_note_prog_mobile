package com.example.tp_flashcard.flashcards

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashCardDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFlashCard(flashCard: FlashCard)


    @Update
    suspend fun updateFlashCard(flashCard: FlashCard)

    @Delete
    suspend fun deleteFlashCard(flashCard: FlashCard)

    @Query("SELECT * from flashCards WHERE id = :id")
    fun getFlashCard(id: Int): Flow<FlashCard>

    @Query("SELECT * from flashCards WHERE categoryId = :categoryId")
    fun getFlashCardByCategory(categoryId: Int): List<FlashCard>

    abstract fun getAllFlashCards(): List<FlashCard>

    abstract fun getAllFlashCardCategories(): List<FlashCardCategory>
    abstract fun getFlashCardCategory(id: Int): FlashCardCategory?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertFlashCardCategory(item: FlashCardCategory)

    @Update
    abstract fun updateFlashCardCategory(item: FlashCardCategory)

    @Delete
    abstract fun deleteFlashCardCategory(item: FlashCardCategory)

}