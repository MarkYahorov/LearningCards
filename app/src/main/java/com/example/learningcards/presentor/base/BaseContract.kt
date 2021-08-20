package com.example.learningcards.presentor.base

interface BaseContract {

    interface View {

    }

    interface Presenter<View : BaseContract.View> {
        fun attach(view: View)
        fun detach()
    }
}