package com.example.learningcards.repository

import android.database.Cursor
import com.example.learningcards.App
import com.example.learningcards.data.DataBase.Companion.CATEGORY_ID
import com.example.learningcards.data.DataBase.Companion.CATEGORY_NAME
import com.example.learningcards.data.DataBase.Companion.FIRST_WORD
import com.example.learningcards.data.DataBase.Companion.ID
import com.example.learningcards.data.DataBase.Companion.NAME_OF_CATEGORY
import com.example.learningcards.data.DataBase.Companion.SECOND_WORD
import com.example.learningcards.data.DataBase.Companion.TRANSLATE_WORD_TABLE_NAME
import com.example.learningcards.data.helpersDb.InsertIntoDBHelper
import com.example.learningcards.data.helpersDb.SelectFromDbHelper
import com.example.learningcards.models.Category
import com.example.learningcards.models.TranslateWord
import com.example.learningcards.models.TranslateWordIndex
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.Callable

class DbRepositoryImpl : DbRepository {

    override fun getListCategories(): Observable<List<Category>> {
        val listOfCategory = mutableListOf<Category>()

        val callable = Callable {
            var cursor: Cursor? = null
            try {
                cursor = SelectFromDbHelper().nameOfTable(CATEGORY_NAME)
                    .selectParams("*")
                    .select(App.INSTANCE.db)
                if (cursor.moveToFirst()) {
                    val idIndex = cursor.getColumnIndexOrThrow(ID)
                    val nameIndex = cursor.getColumnIndexOrThrow(NAME_OF_CATEGORY)
                    do {
                        val id = cursor.getInt(idIndex)
                        val name = cursor.getString(nameIndex)
                        listOfCategory.add(Category(id, name))
                    } while (cursor.moveToNext())
                }
            } finally {
                cursor?.close()
            }
            listOfCategory
        }

        return Observable.fromCallable(callable)
    }

    override fun addCategory(
        name: String,
        listOfTranslateWords: List<TranslateWord>,
    ): Observable<Unit> {
        val callable = Callable {
            InsertIntoDBHelper()
                .setTableName(CATEGORY_NAME)
                .addFieldsAndValuesToInsert(NAME_OF_CATEGORY, name)
                .insertTheValues(App.INSTANCE.db)

            var cursor: Cursor? = null
            var id = 0
            try {
                cursor = SelectFromDbHelper().nameOfTable(CATEGORY_NAME)
                    .selectParams("max($ID) as $ID")
                    .select(App.INSTANCE.db)
                if (cursor.moveToFirst()) {
                    val idIndex = cursor.getColumnIndexOrThrow(ID)
                    do {
                        id = cursor.getInt(idIndex)
                    } while (cursor.moveToNext())
                }
                listOfTranslateWords.forEach {
                    InsertIntoDBHelper()
                        .setTableName(TRANSLATE_WORD_TABLE_NAME)
                        .addFieldsAndValuesToInsert(FIRST_WORD, it.firstWord)
                        .addFieldsAndValuesToInsert(SECOND_WORD, it.secondWord)
                        .addFieldsAndValuesToInsert(CATEGORY_ID, id.toString())
                        .insertTheValues(App.INSTANCE.db)
                }
            } finally {
                cursor?.close()
            }
        }
        return Observable.fromCallable(callable)
    }

    override fun getListOfTranslateWords(id: Int): Observable<List<TranslateWord>> {
        val listOfTranslateWord = mutableListOf<TranslateWord>()
        val callable = Callable {
            var cursor: Cursor? = null
            try {
                cursor = SelectFromDbHelper()
                    .nameOfTable(TRANSLATE_WORD_TABLE_NAME)
                    .selectParams("*")
                    .where("$CATEGORY_ID = $id")
                    .select(App.INSTANCE.db)
                if (cursor.moveToFirst()) {
                    val index = createTranslateWordIndex(cursor)
                    do {
                        val translateWord = createTranslateWordFromDb(index, cursor)
                        listOfTranslateWord.add(translateWord)
                    } while (cursor.moveToNext())
                }
            } finally {
                cursor?.close()
            }
            listOfTranslateWord
        }
        return Observable.fromCallable(callable)
    }

    override fun addTranslateWords(): Observable<Unit> {
        TODO("Not yet implemented")
    }

    private fun createTranslateWordIndex(cursor: Cursor) = TranslateWordIndex(
        idIndex = cursor.getColumnIndexOrThrow(ID),
        firstWordIndex = cursor.getColumnIndexOrThrow(FIRST_WORD),
        secondWordIndex = cursor.getColumnIndexOrThrow(SECOND_WORD),
        categoryIdIndex = cursor.getColumnIndexOrThrow(CATEGORY_ID)
    )

    private fun createTranslateWordFromDb(index: TranslateWordIndex, cursor: Cursor) =
        TranslateWord(
            id = cursor.getInt(index.idIndex),
            firstWord = cursor.getString(index.firstWordIndex),
            secondWord = cursor.getString(index.secondWordIndex),
            categoryId = cursor.getInt(index.categoryIdIndex)
        )
}