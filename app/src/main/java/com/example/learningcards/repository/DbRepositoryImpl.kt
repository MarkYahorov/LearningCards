package com.example.learningcards.repository

import android.database.Cursor
import com.example.learningcards.App
import com.example.learningcards.data.DataBase.Companion.CATEGORY_NAME
import com.example.learningcards.data.DataBase.Companion.ID
import com.example.learningcards.data.DataBase.Companion.NAME_OF_CATEGORY
import com.example.learningcards.data.helpersDb.SelectFromDbHelper
import com.example.learningcards.models.Category
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
}