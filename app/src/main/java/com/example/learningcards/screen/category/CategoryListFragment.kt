package com.example.learningcards.screen.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningcards.R
import com.example.learningcards.models.Category
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CategoryListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var addCategoryBtn: FloatingActionButton? = null

    private val categoryList = mutableListOf<Category>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_list, container, false)
        initAll(view = view)
        return view
    }

    private fun initAll(view: View) {
        recyclerView = view.findViewById(R.id.category_recycler)
        addCategoryBtn = view.findViewById(R.id.add_category_btn)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        with(recyclerView) {
            this?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            this?.adapter = CategoryListAdapter(categoryList = categoryList){

            }
        }
    }

}