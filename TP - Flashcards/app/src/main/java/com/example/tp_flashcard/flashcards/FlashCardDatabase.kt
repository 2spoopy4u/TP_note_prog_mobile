package com.example.tp_flashcard.flashcards

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FlashCard::class, FlashCardCategory::class], version = 1, exportSchema = false)
abstract class FlashCardDatabase : RoomDatabase() {
    abstract fun flashCardDao(): FlashCardDAO

    abstract fun flashCardCategoryDao(): FlashCardCategoryDAO

    companion object {
        @Volatile
        private var Instance: FlashCardDatabase? = null
        fun getDatabase(context: Context): FlashCardDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FlashCardDatabase::class.java, "flashCard_database")
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }

}