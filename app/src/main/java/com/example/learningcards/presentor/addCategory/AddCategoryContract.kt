package com.example.learningcards.presentor.addCategory

import com.example.learningcards.AddCategoryNavigator
import com.example.learningcards.NavigatorInCategoryScreen
import com.example.learningcards.models.TranslateWord
import com.example.learningcards.presentor.base.BaseContract

interface AddCategoryContract {

    interface AddCategoryView : BaseContract.View {
        fun showError(error: String?)
        fun showResult(message: String)
        fun setData(list: List<TranslateWord>)
    }

    interface AddCategoryPresenter : BaseContract.Presenter<AddCategoryView> {
        fun addCategory(name: String, listOfTranslateWords: List<TranslateWord>)
        fun loadAllWordsToCurrentCategory(id: Int)
        fun addListOFWords(
            id: Int,
            listOfTranslateWords: List<TranslateWord>,
            closeScreen: AddCategoryNavigator?,
        )
    }
}