package com.example.tokitokiinventorymanagementandroid.manager.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.LoginActivity
import com.example.tokitokiinventorymanagementandroid.R
import com.example.tokitokiinventorymanagementandroid.helpers.BottomNavigationInitialization
import com.example.tokitokiinventorymanagementandroid.manager.TestRequest
import com.example.tokitokiinventorymanagementandroid.manager.orders.OrdersCustomerOrderDetails
import com.example.tokitokiinventorymanagementandroid.manager.stock.StockActivity
import com.example.tokitokiinventorymanagementandroid.manager.supplier.SupplierActivity
import com.example.tokitokiinventorymanagementandroid.manager.supplier.SupplierAllDeliveries
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var btnRestock: Button
    lateinit var btnShowRecentOrder: Button
    lateinit var btnShowAllShipments: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_home)

        // Bottom Navigation Bar DO NOT TOUCH
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigationInitialization.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navbar_manager_home

        initializeNavigationButtons()

        // TODO: Turn these authentication code into a helper class
        val auth = Firebase.auth
        val tempLogout = findViewById<Button>(R.id.tempLogout)
        val user = auth.currentUser

        // Testing real-time data syncing here
//        startActivity(Intent(this, TestRequest::class.java))

        // Extra measure to make sure the app goes to login screen if there is no user logged in
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        tempLogout.setOnClickListener {
            Log.d("Auth", "User logging out")
            Firebase.auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.navbar_manager_home
    }

    // to keep onCreate CLEAN
    fun initializeNavigationButtons() {
        btnRestock = findViewById<Button>(R.id.btnRestock)
        btnShowRecentOrder = findViewById<Button>(R.id.btnShowRecentOrder)
        btnShowAllShipments = findViewById<TextView>(R.id.btnShowAllShipments)

        btnRestock.setOnClickListener {
            startActivity(Intent(this, StockActivity::class.java))
        }

        btnShowRecentOrder.setOnClickListener {
            startActivity(Intent(this, OrdersCustomerOrderDetails::class.java))
        }

        btnShowAllShipments.setOnClickListener {
            startActivity(Intent(this, SupplierAllDeliveries::class.java))
        }
    }
}