package com.example.tokitokiinventorymanagementandroid.manager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.tokitokiinventorymanagementandroid.LoginActivity
import com.example.tokitokiinventorymanagementandroid.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class TestRequest : Activity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_request)

        val tempLogout = findViewById<Button>(R.id.tempLogout)

        tempLogout.setOnClickListener {
            Log.d("Auth", "User logging out")
            Firebase.auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}