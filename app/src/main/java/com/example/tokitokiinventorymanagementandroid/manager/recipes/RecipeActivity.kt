package com.example.tokitokiinventorymanagementandroid.manager.recipes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tokitokiinventorymanagementandroid.R
import com.example.tokitokiinventorymanagementandroid.helpers.BottomNavigationInitialization
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecipeActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_recipe_overview)

        // Bottom Navigation Bar DO NOT TOUCH
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigationInitialization.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navbar_manager_recipe

        val btnAddRecipe = findViewById<Button>(R.id.addRecipeBtn)
        val btnEditRecipe = findViewById<Button>(R.id.editBtn)

        btnAddRecipe.setOnClickListener {
            startActivity(Intent(this, AddRecipe::class.java))
        }

        btnEditRecipe.setOnClickListener {
            startActivity(Intent(this, EditRecipe::class.java))
        }
    }

    // THIS FUNCTION ENSURES THAT THE APPROPRIATE ICON IS CHECKED EVEN WHEN YOU PRESS THE BACK BUTTON
    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.navbar_manager_recipe
    }
}