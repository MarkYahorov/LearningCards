package com.example.learningcards.presentor.category

import com.example.learningcards.App
import com.example.learningcards.presentor.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryScreenPresenter: BasePresenter<CategoryScreenContract.ViewCategoryList>(), CategoryScreenContract.PresenterCategoryList {
    override fun loadCategoryList() {
        getCompositeDisposable().add(
            App.INSTANCE.repoCategory.getListCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ response ->
                    getView().setData(response)
                }, { error ->
                    getView().showError(error.message)
                })
        )
    }
}