package com.example.learningcards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learningcards.screen.add.AddCategoryFragment
import com.example.learningcards.screen.category.CategoryFragment

class MainActivity : AppCompatActivity(), NavigatorInCategoryScreen {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState== null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CategoryFragment())
                .commit()
        }
    }

    override fun goToAddCategoryScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AddCategoryFragment())
            .commit()
    }

    override fun goToCurrentCategoryScreen() {
        TODO("Not yet implemented")
    }
}