package com.example.tokitokiinventorymanagementandroid.manager.recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tokitokiinventorymanagementandroid.R

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_recipe_overview)

        val btnAddRecipe = findViewById<Button>(R.id.addRecipeBtn)
        val btnEditRecipe = findViewById<Button>(R.id.editBtn)

        btnAddRecipe.setOnClickListener {
            startActivity(Intent(this, AddRecipe::class.java))
        }

        btnEditRecipe.setOnClickListener {
            startActivity(Intent(this, EditRecipe::class.java))
        }
    }
}