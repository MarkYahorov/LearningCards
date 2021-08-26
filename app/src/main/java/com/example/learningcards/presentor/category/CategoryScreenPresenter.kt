package com.example.learningcards.presentor.category

import com.example.learningcards.presentor.base.BasePresenter
import com.example.learningcards.repository.DbRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryScreenPresenter(private val repo: DbRepository): BasePresenter<CategoryScreenContract.ViewCategoryList>(), CategoryScreenContract.PresenterCategoryList {
    override fun loadCategoryList() {
        getCompositeDisposable().add(
            repo.getListCategories()
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