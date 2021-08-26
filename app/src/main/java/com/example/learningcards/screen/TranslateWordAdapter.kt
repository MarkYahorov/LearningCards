package com.example.learningcards.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.learningcards.R
import com.example.learningcards.models.TranslateWord

class TranslateWordAdapter(
    private val list: List<TranslateWord>,
    private val addTranslate: (TranslateWord) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ADD_ITEM_VIEW_TYPE = 1
        private const val TRANSLATE_ITEM_VIEW_TYPE = 0
    }

    inner class TranslateHolder(item: View, private val addTranslate: (TranslateWord) -> Unit) :
        RecyclerView.ViewHolder(item) {
        private val addBtn = item.findViewById<TextView>(R.id.add_btn)

        fun bind(translateWord: TranslateWord) {
            addBtn.setOnClickListener {
                addTranslate(translateWord)
                addBtn.isVisible = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ADD_ITEM_VIEW_TYPE){
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.add_item, parent, false
            )
            AddHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.translate_item, parent, false
            )
            TranslateHolder(view, addTranslate)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = list.size

    inner class AddHolder(item:View): RecyclerView.ViewHolder(item){

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) {
            ADD_ITEM_VIEW_TYPE
        } else {
            TRANSLATE_ITEM_VIEW_TYPE
        }
    }
}