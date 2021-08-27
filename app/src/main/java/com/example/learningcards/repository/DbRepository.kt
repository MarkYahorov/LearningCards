package com.example.learningcards.repository

import com.example.learningcards.models.Category
import com.example.learningcards.models.TranslateWord
import io.reactivex.rxjava3.core.Observable

interface DbRepository {

    fun getListCategories(): Observable<List<Category>>
    fun addCategory(name: String, listOfTranslateWords: List<TranslateWord>): Observable<Unit>
    fun getListOfTranslateWords(id: Int): Observable<List<TranslateWord>>
    fun addTranslateWords(): Observable<Unit>
}