package com.example.tokitokiinventorymanagementandroid.Login

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.R
import com.example.tokitokiinventorymanagementandroid.helpers.SpinnerInitializations

class LoginActivity : AppCompatActivity() {
//    val roles = arrayOf("Manager", "Supplier")
    lateinit var spinnerLoginType : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        spinnerLoginType = findViewById(R.id.spinnerLoginType)


        SpinnerInitializations.loginSpinner(this, spinnerLoginType)





    }

}