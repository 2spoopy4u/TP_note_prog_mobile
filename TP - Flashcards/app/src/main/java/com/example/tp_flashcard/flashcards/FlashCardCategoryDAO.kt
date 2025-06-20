package com.example.tp_flashcard.flashcards

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashCardCategoryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(flashCardCategory: FlashCardCategory): Long

    @Update
    suspend fun update(flashCardCategory: FlashCardCategory)

    @Delete
    suspend fun delete(flashCardCategory: FlashCardCategory)

    @Query("SELECT * from FlashCardCategory WHERE id = :id")
    fun getFlashCardCategory(id: Int): Flow<FlashCardCategory>

    @Query("SELECT * from FlashCardCategory")
    fun getAllFlashCardCategory(): Flow<List<FlashCardCategory>>

}