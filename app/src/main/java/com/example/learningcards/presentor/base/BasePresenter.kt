package com.example.learningcards.presentor.base

import com.example.learningcards.repository.DbRepository
import com.example.learningcards.repository.DbRepositoryImpl

abstract class BasePresenter<View:BaseContract.View>: BaseContract.Presenter<View> {

    var view:View? = null
    var repo: DbRepository? = null

    override fun attach(view: View) {
        this.view = view
        this.repo = DbRepositoryImpl()
    }

    override fun detach() {
        this.view = null
        this.repo = null
    }

}