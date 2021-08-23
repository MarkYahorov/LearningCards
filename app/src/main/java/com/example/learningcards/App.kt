package com.example.learningcards

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import com.example.learningcards.data.DataBase
import com.example.learningcards.repository.DbRepository
import com.example.learningcards.repository.DbRepositoryImpl

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
    }

    lateinit var db: SQLiteDatabase
    lateinit var repoCategory: DbRepository

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        db = DataBase(this).writableDatabase
        repoCategory = DbRepositoryImpl()
    }
}