package com.example.learningcards.screen.currentCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learningcards.App
import com.example.learningcards.R
import com.example.learningcards.models.TranslateWord
import com.example.learningcards.presentor.currentCategory.CurrentCategoryContract
import com.example.learningcards.presentor.currentCategory.CurrentCategoryPresenter
import com.example.learningcards.screen.BaseFragment


class CurrentCategory : BaseFragment<CurrentCategoryContract.CurrentCategoryPresenter,CurrentCategoryContract.CurrentCategoryView>(), CurrentCategoryContract.CurrentCategoryView{



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_category, container, false)
    }

    override fun createPresenter(): CurrentCategoryContract.CurrentCategoryPresenter {
        return CurrentCategoryPresenter(App.INSTANCE.repoCategory)
    }

    override fun showError(error: String?) {
        TODO("Not yet implemented")
    }

    override fun setData(listOfTranslateWords: List<TranslateWord>) {
        TODO("Not yet implemented")
    }
}