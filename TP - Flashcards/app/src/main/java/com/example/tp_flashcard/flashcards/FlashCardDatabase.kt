package com.example.tp_flashcard.flashcards

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [FlashCard::class, FlashCardCategory::class], version = 1, exportSchema = false)
abstract class FlashCardDatabase : RoomDatabase() {
    abstract fun flashCardDao(): FlashCardDAO

    abstract fun flashCardCategoryDao(): FlashCardCategoryDAO

    companion object {
        @Volatile
        private var Instance: FlashCardDatabase? = null
        fun getDatabase(context: Context): FlashCardDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FlashCardDatabase::class.java,
                    "flashCard_database"
                )
                    .fallbackToDestructiveMigration(false)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // ⚠️ Ne pas faire les insertions ici directement (thread UI)
                            CoroutineScope(Dispatchers.IO).launch {
                                val database = getDatabase(context)
                                val categoryDao = database.flashCardCategoryDao()
                                val flashCardDao = database.flashCardDao()

                                prepopulateDatabase(categoryDao, flashCardDao)
                            }
                        }
                    })
                    .build()
                    .also { Instance = it }
            }
        }

        suspend fun prepopulateDatabase(categoryDao: FlashCardCategoryDAO, flashCardDao: FlashCardDAO) {
            val categories = listOf("Maths", "Histoire", "Géographie", "Sciences")

            for (categoryName in categories) {
                val categoryId = categoryDao.insert(FlashCardCategory(name = categoryName)).toInt()

                for (i in 1..5) {
                    val question = when (categoryName) {
                        "Maths" -> "Quel est le résultat de $i + ${i} ?"
                        "Histoire" -> "En quelle année a eu lieu l'événement $i ?"
                        "Géographie" -> "Quelle est la capitale du pays numéro $i ?"
                        "Sciences" -> "Quelle est la formule chimique de l'élément $i ?"
                        else -> "Question $i"
                    }
                    val answer = when (categoryName) {
                        "Maths" -> "${i + i}"
                        "Histoire" -> "Année $i"
                        "Géographie" -> "Capitale $i"
                        "Sciences" -> "Formule $i"
                        else -> "Réponse $i"
                    }

                    flashCardDao.insertFlashCard(
                        FlashCard(
                            question = question,
                            answer = answer,
                            categoryId = categoryId
                        )
                    )
                }
            }
        }
    }


}