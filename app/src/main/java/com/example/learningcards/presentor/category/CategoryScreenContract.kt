package com.example.learningcards.presentor.category

import com.example.learningcards.models.Category
import com.example.learningcards.presentor.base.BaseContract

interface CategoryScreenContract {

    interface ViewCategoryList : BaseContract.View {
        fun showError(error: String?)
        fun setData(list: List<Category>)
    }


    interface PresenterCategoryList : BaseContract.Presenter<ViewCategoryList> {
        fun loadCategoryList()
    }
}