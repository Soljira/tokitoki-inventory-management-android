package com.example.tokitokiinventorymanagementandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.manager.recipes.RecipeActivity
import com.example.tokitokiinventorymanagementandroid.manager.supplier.SupplierActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        // Intent initializations
        val intentLogin = Intent(this, LoginActivity::class.java)
        val intentRecipe = Intent(this, RecipeActivity::class.java)
        val intentSupplier = Intent(this, SupplierActivity::class.java)

        startActivity(intentSupplier)

    }
}