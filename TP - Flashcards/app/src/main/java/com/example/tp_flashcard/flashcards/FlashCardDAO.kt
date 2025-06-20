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
    suspend fun insertFlashCard(flashCard: FlashCard): Long


    @Update
    suspend fun updateFlashCard(flashCard: FlashCard)

    @Delete
    suspend fun deleteFlashCard(flashCard: FlashCard)

    @Query("SELECT * from FlashCard WHERE id = :id")
    fun getFlashCard(id: Int): Flow<FlashCard>

    @Query("SELECT * from FlashCard WHERE categoryId = :categoryId")
    fun getFlashCardByCategory(categoryId: Int): Flow<List<FlashCard>>

}