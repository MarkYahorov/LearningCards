package com.example.learningcards.presentor.addCategory

import com.example.learningcards.models.TranslateWord
import com.example.learningcards.presentor.base.BasePresenter
import com.example.learningcards.repository.DbRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddCategoryPresenter(private val repo: DbRepository): BasePresenter<AddCategoryContract.AddCategoryView>(), AddCategoryContract.AddCategoryPresenter {

    override fun addCategory(name:String, listOfTranslateWords:List<TranslateWord>) {
        getCompositeDisposable().add(
            repo.addCategory(name, listOfTranslateWords)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    getView().showResult("All complete")
                }, {
                    getView().showError(it.message)
                })
        )
    }
}