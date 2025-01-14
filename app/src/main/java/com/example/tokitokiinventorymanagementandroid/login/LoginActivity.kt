package com.example.tokitokiinventorymanagementandroid.login

import android.os.Bundle
import android.widget.Spinner
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