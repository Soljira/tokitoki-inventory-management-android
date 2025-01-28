package com.example.tokitokiinventorymanagementandroid.supplier.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.LoginActivity
//import com.google.firebase.auth.FirebaseAuth
import com.example.tokitokiinventorymanagementandroid.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.supplier_home)

        // TODO: Turn these authentication code into a helper class
        val auth = Firebase.auth
        val tempLogout = findViewById<Button>(R.id.tempLogout)
        val user = auth.currentUser

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
}
