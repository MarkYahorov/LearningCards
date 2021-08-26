package com.example.learningcards.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.learningcards.presentor.base.BaseContract

abstract class BaseFragment<Presenter : BaseContract.Presenter<View>, View : BaseContract.View> :
    Fragment() {

    private var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.attach(getMVPView())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.presenter = null
    }

    abstract fun createPresenter(): Presenter

    open fun getMVPView(): View {
        return this as? View ?: error("noView")
    }

    protected fun getPresenter(): Presenter {
        return presenter ?: error("Presenter is not created")
    }
}