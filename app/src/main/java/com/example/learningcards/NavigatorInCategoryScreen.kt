package com.example.learningcards

import com.example.learningcards.models.Category

interface NavigatorInCategoryScreen {
    fun goToAddCategoryScreen()
    fun goToCurrentCategoryScreen(category: Category)
}