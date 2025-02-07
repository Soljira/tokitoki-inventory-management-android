package com.example.tokitokiinventorymanagementandroid.manager.orders

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.R

class OrdersCustomerOrderDetails : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_orders_customer_order_details)

        val btnClose = findViewById<Button>(R.id.btnClose)

        btnClose.setOnClickListener {
            finish()
        }
    }

}