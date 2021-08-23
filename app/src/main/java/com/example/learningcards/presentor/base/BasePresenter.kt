package com.example.learningcards.presentor.base

import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BasePresenter<View : BaseContract.View> : BaseContract.Presenter<View> {

    private var view: View? = null
    private var disposable: CompositeDisposable? = null

    override fun attach(view: View) {
        this.view = view
        this.disposable = CompositeDisposable()
    }

    override fun detach() {
        this.disposable?.dispose()
        this.disposable = null
        this.view = null
    }

    protected fun isViewAttached(): Boolean {
        return view != null
    }

    protected fun getView(): View {
        return view ?: error("View is not attached")
    }

    protected fun getCompositeDisposable(): CompositeDisposable {
        return disposable ?: error("is not created")
    }
}