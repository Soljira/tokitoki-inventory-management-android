package com.example.tokitokiinventorymanagementandroid.manager.orders

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
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


        // NOTE: This is just an example. As more order items are added, definitely do not
        // hardcode each order item card here. Get data from Firestore and use loops to populate
        // the list - Breiah
        val orderItem0 = findViewById<LinearLayout>(R.id.orderItem0)
        val orderItem1 = findViewById<LinearLayout>(R.id.orderItem1)

        orderItem0.setOnClickListener {
            startActivity(Intent(this, OrdersCustomerOrderDetails::class.java))
        }

        orderItem1.setOnClickListener {
            startActivity(Intent(this, OrdersCustomerOrderDetails::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.navbar_manager_orders
    }
}