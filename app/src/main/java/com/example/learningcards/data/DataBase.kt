package com.example.learningcards.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.learningcards.data.helpersDb.CreateTableHelper

const val LEARN_DB = "LEARN DB"
const val VERSION_DB = 1

class DataBase(context: Context): SQLiteOpenHelper(context, LEARN_DB, null, VERSION_DB) {

    companion object {
        const val CATEGORY_NAME = "category"
        const val ID = "id"
        const val NAME_OF_CATEGORY = "name"
        private const val INTEGER_NOT_NULL_PRIMARY_KEY_AUTOINCREMENT =
            "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
        private const val STRING_NOT_NULL = "TEXT NOT NULL"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createCategoryTable(db = db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    private fun createCategoryTable(db: SQLiteDatabase?){
        CreateTableHelper()
            .setName(CATEGORY_NAME)
            .addField(ID, INTEGER_NOT_NULL_PRIMARY_KEY_AUTOINCREMENT)
            .addField(NAME_OF_CATEGORY, STRING_NOT_NULL)
            .create(db = db)
    }
}