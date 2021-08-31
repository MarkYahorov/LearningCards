package com.example.learningcards.screen.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningcards.AddCategoryNavigator
import com.example.learningcards.App
import com.example.learningcards.R
import com.example.learningcards.models.Category
import com.example.learningcards.models.TranslateWord
import com.example.learningcards.presentor.addCategory.AddCategoryContract
import com.example.learningcards.presentor.addCategory.AddCategoryPresenter
import com.example.learningcards.screen.BaseFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddOrCurrentCategoryFragment :
    BaseFragment<AddCategoryContract.AddCategoryPresenter, AddCategoryContract.AddCategoryView>(),
    AddCategoryContract.AddCategoryView {

    companion object {
        private const val CURRENT_CATEGORY = "CURRENT_CATEGORY"
        private const val OLD_LIST_SIZE = "OLD_LIST_SIZE"
        fun newInstance(category: Category?): AddOrCurrentCategoryFragment {
            val addCategoryFragment = AddOrCurrentCategoryFragment()
            val bundle = Bundle()
            bundle.putParcelable(CURRENT_CATEGORY, category)
            addCategoryFragment.arguments = bundle
            return addCategoryFragment
        }
    }

    private var categoryName: EditText? = null
    private var addCategoryBtn: Button? = null
    private var cancelCategoryBtn: Button? = null
    private var translateRecycler: RecyclerView? = null
    private var nameOfCategory: TextView? = null
    private var fab: FloatingActionButton? = null
    private val translateList = mutableListOf<TranslateWord>()
    private var closeScreen: AddCategoryNavigator? = null
    private var oldListSize = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        closeScreen = context as AddCategoryNavigator
    }

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
        nameOfCategory = view.findViewById(R.id.name_cat)
        fab = view.findViewById(R.id.save_list_in_current)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        checkCategory()
        if (savedInstanceState == null) {
            oldListSize = translateList.size
        } else {
            oldListSize = savedInstanceState.getInt(OLD_LIST_SIZE)
        }
    }

    private fun checkCategory() {
        val category = arguments?.getParcelable<Category>(CURRENT_CATEGORY)
        if (category != null) {
            nameOfCategory?.isVisible = true
            nameOfCategory?.text = category.name
            addCategoryBtn?.isVisible = false
            cancelCategoryBtn?.isVisible = false
            fab?.isVisible = true
            getPresenter().loadAllWordsToCurrentCategory(category.id)
            fabListener(category)
        }
    }

    private fun fabListener(category: Category){
        fab?.setOnClickListener {
            val newWordList = mutableListOf<TranslateWord>()
            oldListSize = translateList.size - oldListSize
            while (oldListSize!=0){
                newWordList.add(translateList[translateList.size-oldListSize])
                oldListSize-1
            }
            getPresenter().addListOFWords(category.id, newWordList,closeScreen)
        }
    }

    private fun initRecycler() {
        with(translateRecycler) {
            this?.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this?.adapter = TranslateWordAdapter(list = translateList,
                addTranslate = {

                }, addNewItem = {
                    translateList.add(TranslateWord(translateList.size + 1, null, null, null, true))
                    this?.adapter?.notifyDataSetChanged()
                }, showDialogNoEmptyString = {
                    createAlert("Enter all words")
                })
        }
    }

    override fun onStart() {
        super.onStart()
        saveBtnListener()
        cancelBtnListener()
    }

    private fun saveBtnListener() {
        addCategoryBtn?.setOnClickListener {
            if (!categoryName?.text.isNullOrBlank()) {
                getPresenter().addCategory(categoryName?.text.toString(), translateList)
                closeScreen?.closeScreen()
            } else {
                createAlert("Enter the name of category")
            }
        }
    }

    private fun cancelBtnListener() {
        cancelCategoryBtn?.setOnClickListener {
            closeScreen?.closeScreen()
        }
    }

    override fun createPresenter(): AddCategoryContract.AddCategoryPresenter {
        return AddCategoryPresenter(App.INSTANCE.repoCategory)
    }

    override fun showError(error: String?) {
        createAlert(error)
    }

    override fun showResult(message: String) {
        createAlert(message)
    }

    override fun setData(list: List<TranslateWord>) {
        translateList.addAll(list)
        translateRecycler?.adapter?.notifyDataSetChanged()
    }

    private fun createAlert(error: String?): AlertDialog? {
        return AlertDialog.Builder(requireContext())
            .setMessage(error)
            .show()
    }
}