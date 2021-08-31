package com.example.learningcards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learningcards.models.Category
import com.example.learningcards.screen.add.AddOrCurrentCategoryFragment
import com.example.learningcards.screen.category.CategoryFragment

class MainActivity : AppCompatActivity(), NavigatorInCategoryScreen, AddCategoryNavigator {

    companion object {
        private const val ADD_CATEGORY_SCREEN = "ADD_CATEGORY_SCREEN"
        private const val CATEGORY_SCREEN = "CATEGORY_SCREEN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CategoryFragment())
                .addToBackStack(CATEGORY_SCREEN)
                .commit()
        }
    }

    override fun goToAddCategoryScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AddOrCurrentCategoryFragment.newInstance(null))
            .addToBackStack(ADD_CATEGORY_SCREEN)
            .commit()
    }

    override fun goToCurrentCategoryScreen(category: Category) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AddOrCurrentCategoryFragment.newInstance(category))
            .addToBackStack(ADD_CATEGORY_SCREEN)
            .commit()
    }

    override fun closeScreen() {
        onBackPressed()
    }
}