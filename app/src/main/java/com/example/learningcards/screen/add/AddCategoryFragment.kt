package com.example.learningcards.screen.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningcards.R
import com.example.learningcards.models.TranslateWord
import com.example.learningcards.screen.TranslateWordAdapter

class AddCategoryFragment : Fragment() {

    private var categoryName: EditText? = null
    private var addCategoryBtn: Button? = null
    private var cancelCategoryBtn: Button? = null
    private var translateRecycler: RecyclerView? = null
    private val translateList = mutableListOf<TranslateWord>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_category, container, false)
        initAll(view = view)
        return view
    }

    private fun initAll(view: View) {
        categoryName = view.findViewById(R.id.category_name)
        addCategoryBtn = view.findViewById(R.id.save_category_btn)
        cancelCategoryBtn = view.findViewById(R.id.cancel_category_btn)
        translateRecycler = view.findViewById(R.id.translate_recycler)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        with(translateRecycler){
            this?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this?.adapter = TranslateWordAdapter(translateList){

            }
        }
    }
}