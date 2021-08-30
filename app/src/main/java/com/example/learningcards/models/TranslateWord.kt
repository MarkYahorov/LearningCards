package com.example.learningcards.models

data class TranslateWord(
    val id: Int,
    var firstWord: String?,
    var secondWord: String?,
    var categoryId: Int?,
    var isNewWord: Boolean = false
)
