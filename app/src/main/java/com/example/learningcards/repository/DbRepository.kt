package com.example.learningcards.repository

import com.example.learningcards.models.Category
import io.reactivex.rxjava3.core.Observable

interface DbRepository {

    fun getListCategories(): Observable<List<Category>>
}