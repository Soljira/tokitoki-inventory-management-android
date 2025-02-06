package com.example.tokitokiinventorymanagementandroid.manager.supplier

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.R
import com.example.tokitokiinventorymanagementandroid.helpers.BottomNavigationInitialization
import com.google.android.material.bottomnavigation.BottomNavigationView

class SupplierActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_supplier_status_overview)

        // Bottom Navigation Bar DO NOT TOUCH
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        BottomNavigationInitialization.setupBottomNavigation(this, bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.navbar_manager_supplier

        val btnManageSuppliers = findViewById<Button>(R.id.btnManage)
        val btnRequest = findViewById<Button>(R.id.btnRequest)

        btnManageSuppliers.setOnClickListener {
            startActivity(Intent(this, SupplierManageSuppliers::class.java))
        }

        btnRequest.setOnClickListener {
            startActivity(Intent(this, SupplierRequestDelivery::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView.selectedItemId = R.id.navbar_manager_supplier
    }
}