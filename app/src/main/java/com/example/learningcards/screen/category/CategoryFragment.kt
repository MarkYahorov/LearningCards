package com.example.learningcards.screen.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningcards.App
import com.example.learningcards.NavigatorInCategoryScreen
import com.example.learningcards.R
import com.example.learningcards.models.Category
import com.example.learningcards.presentor.category.CategoryScreenContract
import com.example.learningcards.presentor.category.CategoryScreenPresenter
import com.example.learningcards.screen.BaseFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CategoryFragment :
    BaseFragment<CategoryScreenContract.PresenterCategoryList, CategoryScreenContract.ViewCategoryList>(),
    CategoryScreenContract.ViewCategoryList {

    private var recyclerView: RecyclerView? = null
    private var fab: FloatingActionButton? = null
    private val list = mutableListOf<Category>()
    private var navigator: NavigatorInCategoryScreen? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as NavigatorInCategoryScreen
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_list, container, false)
        initAll(view = view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        getPresenter().loadCategoryList()
    }

    override fun onStart() {
        super.onStart()
        fabListener()
    }

    private fun initRecycler() {
        with(recyclerView) {
            this?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this?.adapter = CategoryListAdapter(list) {
                navigator?.goToCurrentCategoryScreen(it.id)
            }
        }
    }

    private fun initAll(view: View) {
        recyclerView = view.findViewById(R.id.category_recycler)
        fab = view.findViewById(R.id.add_category_btn)
    }

    override fun createPresenter(): CategoryScreenContract.PresenterCategoryList {
        return CategoryScreenPresenter(App.INSTANCE.repoCategory)
    }

    override fun showError(error: String?) {
        createErrorAlert(error)
    }

    override fun setData(list: List<Category>) {
        this.list.addAll(list)
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun fabListener() {
        fab?.setOnClickListener {
            navigator?.goToAddCategoryScreen()
        }
    }


    private fun createErrorAlert(error: String?): AlertDialog? {
        return AlertDialog.Builder(requireContext())
            .setMessage(error)
            .show()
    }
}