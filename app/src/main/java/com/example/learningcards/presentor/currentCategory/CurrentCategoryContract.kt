package com.example.learningcards.presentor.currentCategory

import com.example.learningcards.models.TranslateWord
import com.example.learningcards.presentor.base.BaseContract

interface CurrentCategoryContract {

    interface CurrentCategoryView : BaseContract.View {
        fun showError(error: String?)
        fun setData(listOfTranslateWords: List<TranslateWord>)
    }

    interface CurrentCategoryPresenter : BaseContract.Presenter<CurrentCategoryView> {
        fun loadTranslateWords(categoryId: Int)
    }
}