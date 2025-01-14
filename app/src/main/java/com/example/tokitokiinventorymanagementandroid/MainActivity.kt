package com.example.tokitokiinventorymanagementandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        // Intent initializations
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
    }
}