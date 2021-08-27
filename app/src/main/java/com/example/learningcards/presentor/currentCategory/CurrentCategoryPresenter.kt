package com.example.learningcards.presentor.currentCategory

import com.example.learningcards.presentor.base.BasePresenter
import com.example.learningcards.repository.DbRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CurrentCategoryPresenter(private val repo: DbRepository) :
    BasePresenter<CurrentCategoryContract.CurrentCategoryView>(),
    CurrentCategoryContract.CurrentCategoryPresenter {
    override fun loadTranslateWords(categoryId: Int) {
        getCompositeDisposable().add(
            repo.getListOfTranslateWords(categoryId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        getView().setData(it)
                    },
                    {
                        getView().showError(it.message)
                    })
        )
    }
}