package com.example.tokitokiinventorymanagementandroid.manager.stock

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
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

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnAddItem = findViewById<Button>(R.id.addItemBtn)

        // NOTE: This is just an example. As more stock items are added, definitely do not
        // hardcode each stock item card here. Get data from Firestore and use loops to populate
        // the list - Breiah
        val stockItem0 = findViewById<LinearLayout>(R.id.stockItem0)

        stockItem0.setOnClickListener {
            startActivity(Intent(this, StockSelectedInventoryItem::class.java))
        }


        btnAddItem.setOnClickListener {
            startActivity(Intent(this, StockAddInventoryItem::class.java))
        }

        btnBack.setOnClickListener {
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.navbar_manager_stock
    }

}