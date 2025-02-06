package com.example.tokitokiinventorymanagementandroid.supplier

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager.Request
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.tokitokiinventorymanagementandroid.LoginActivity
import com.example.tokitokiinventorymanagementandroid.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class TestReceive : Activity() {
    lateinit var quantityListener : TextView

    // FIREBASE WANTS THESE ITEMS TO HAVE DEFAULT VALUES DO NOT REMOVE
    data class Request(
        val managerId: String = "",
        val supplierId: String = "",
        val items: List<Item> = emptyList(),
        val status: String = ""
    )

    data class Item(
        val name: String = "",
        val quantity: Int = 0
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_receive)

        val tempLogout = findViewById<Button>(R.id.tempLogout)

        tempLogout.setOnClickListener {
            Log.d("Auth", "User logging out")
            Firebase.auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        quantityListener = findViewById(R.id.quantityListener)

        checkUpdates()
    }


    fun checkUpdates() {
        val database = Firebase.database.reference
        database.child("requests").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Parse the data from the database
                val requests = snapshot.children.mapNotNull { it.getValue(Request::class.java) }

                // Check if there are any requests
                if (requests.isNotEmpty()) {
                    // For simplicity, let's assume we're interested in the first request
                    val firstRequest = requests[0]

                    // Check if the request has items
                    if (firstRequest.items.isNotEmpty()) {
                        // For simplicity, let's assume we're interested in the first item's quantity
                        val firstItem = firstRequest.items[0]
                        val quantity = firstItem.quantity

                        // Update the TextView with the quantity
                        quantityListener.text = "Quantity: $quantity"
                    } else {
                        quantityListener.text = "No items in the request"
                    }
                } else {
                    quantityListener.text = "No requests found"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                quantityListener.text = "Failed to read data: ${error.message}"
                Log.e("FirebaseError", "Database error: ${error.message}")
            }
        })
    }
}