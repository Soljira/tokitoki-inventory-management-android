package com.example.tokitokiinventorymanagementandroid.manager.stock

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.R
import com.example.tokitokiinventorymanagementandroid.helpers.BottomNavigationInitialization
import com.google.android.material.bottomnavigation.BottomNavigationView

class StockActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_stock_overview)

        // Bottom Navigation Bar DO NOT TOUCH
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigationInitialization.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navbar_manager_stock
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.navbar_manager_stock
    }

}