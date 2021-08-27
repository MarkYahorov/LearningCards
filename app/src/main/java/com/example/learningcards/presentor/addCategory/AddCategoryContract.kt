package com.example.learningcards.presentor.addCategory

import com.example.learningcards.models.TranslateWord
import com.example.learningcards.presentor.base.BaseContract

interface AddCategoryContract {

    interface AddCategoryView: BaseContract.View {
        fun showError(error: String?)
        fun showResult(message: String)
    }

    interface AddCategoryPresenter: BaseContract.Presenter<AddCategoryView> {
        fun addCategory(name:String, listOfTranslateWords:List<TranslateWord>)
    }
}