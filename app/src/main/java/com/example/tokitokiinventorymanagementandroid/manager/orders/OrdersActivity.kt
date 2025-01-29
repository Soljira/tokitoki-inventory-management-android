package com.example.tokitokiinventorymanagementandroid.manager.orders

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tokitokiinventorymanagementandroid.R
import com.example.tokitokiinventorymanagementandroid.helpers.BottomNavigationInitialization
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrdersActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_orders_customer_orders)

        // Bottom Navigation Bar DO NOT TOUCH
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigationInitialization.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navbar_manager_orders
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.navbar_manager_orders
    }
}