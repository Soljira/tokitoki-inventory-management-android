package com.example.firebasetokyo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.auth.FirebaseAuth
import com.example.tokitokiinventorymanagementandroid.R

class ManagerActivity : AppCompatActivity() {
//    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_manager)

//        auth = FirebaseAuth.getInstance()

//        val welcomeText = findViewById<TextView>(R.id.welcomeText)
//        val logoutButton = findViewById<Button>(R.id.logoutButton)

//        welcomeText.text = "Welcome Manager\n${auth.currentUser?.email}"

//        logoutButton.setOnClickListener {
//            auth.signOut()
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }
    }
}
