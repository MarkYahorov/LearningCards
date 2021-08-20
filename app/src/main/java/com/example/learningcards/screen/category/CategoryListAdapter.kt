package com.example.learningcards.screen.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.learningcards.R
import com.example.learningcards.models.Category

class CategoryListAdapter(
    private val categoryList: MutableList<Category>,
    private val openCategoryScreen: (Category) -> Unit,
) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    class ViewHolder(private val item: View, private val openCategoryScreen: (Category) -> Unit) :
        RecyclerView.ViewHolder(item) {
        private val name: TextView = item.findViewById(R.id.name_of_category)

        fun bind(category: Category) {
            name.text = category.name
            item.setOnClickListener {
                openCategoryScreen(category)
            }
        }

        fun unbind(){
            item.setOnClickListener(null)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.unbind()

        super.onViewRecycled(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.category_item, parent, false
        )
        return ViewHolder(item = view, openCategoryScreen = openCategoryScreen)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category = categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size
}