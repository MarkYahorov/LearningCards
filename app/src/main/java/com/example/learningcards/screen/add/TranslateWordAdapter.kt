package com.example.learningcards.screen.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.learningcards.R
import com.example.learningcards.models.TranslateWord

class TranslateWordAdapter(
    private val list: List<TranslateWord>,
    private val addTranslate: (TranslateWord) -> Unit,
    private val addNewItem: () -> Unit,
    private val showDialogNoEmptyString: () -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ADD_ITEM_VIEW_TYPE = 1
        private const val TRANSLATE_ITEM_VIEW_TYPE = 0
    }

    inner class TranslateHolder(
        item: View,
        private val addTranslate: (TranslateWord) -> Unit,
        private val showDialogNoEmptyString: () -> Unit,
    ) :
        RecyclerView.ViewHolder(item) {
        private val addBtn = item.findViewById<TextView>(R.id.add_btn)
        private val firstWord = item.findViewById<EditText>(R.id.first_word)
        private val secondWord = item.findViewById<EditText>(R.id.second_word)

        fun bind(translateWord: TranslateWord) {
            addBtn.setOnClickListener {
                if (!firstWord.text.isNullOrEmpty() && !secondWord.text.isNullOrEmpty()) {
                    translateWord.firstWord = firstWord.text.toString()
                    translateWord.secondWord = secondWord.text.toString()
                    addTranslate(translateWord)
                    addBtn.isVisible = false
                } else {
                    showDialogNoEmptyString()
                }
            }
        }

        fun unbind() {
            addBtn.setOnClickListener(null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ADD_ITEM_VIEW_TYPE) {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.add_item, parent, false
            )
            AddHolder(view, addNewItem)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.translate_item, parent, false
            )
            TranslateHolder(view, addTranslate, showDialogNoEmptyString)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == ADD_ITEM_VIEW_TYPE) {
            (holder as AddHolder).bind()
        } else {
            (holder as TranslateHolder).bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size + 1

    inner class AddHolder(private val item: View, private val addNewItem: () -> Unit) :
        RecyclerView.ViewHolder(item) {

        fun bind() {
            item.setOnClickListener {
                addNewItem()
            }
        }

        fun unbind() {
            item.setOnClickListener(null)
        }

    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder.itemViewType == ADD_ITEM_VIEW_TYPE) {
            (holder as AddHolder).unbind()
        } else {
            (holder as TranslateHolder).unbind()
        }
        super.onViewRecycled(holder)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            ADD_ITEM_VIEW_TYPE
        } else {
            TRANSLATE_ITEM_VIEW_TYPE
        }
    }
}