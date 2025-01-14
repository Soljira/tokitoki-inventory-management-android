package com.example.tokitokiinventorymanagementandroid.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasetokyo.ManagerActivity
import com.example.tokitokiinventorymanagementandroid.R
import com.example.tokitokiinventorymanagementandroid.helpers.SpinnerInitializations
import com.example.tokitokiinventorymanagementandroid.manager.supplier.SupplierActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var userTypeSpinner: Spinner
    private lateinit var loginButton: Button
    private lateinit var emailClearButton: ImageView
    private lateinit var passwordClearButton: ImageView

//    val roles = arrayOf("Manager", "Supplier")
    lateinit var spinnerLoginType : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

//        spinnerLoginType = findViewById(R.id.spinnerLoginType)
//        SpinnerInitializations.loginSpinner(this, spinnerLoginType)



        initializeViews()
        setupSpinner()
        setupClearButtons()
        setupLoginButton()

    }

    private fun initializeViews() {
        userTypeSpinner = findViewById(R.id.userTypeSpinner)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        emailClearButton = findViewById(R.id.emailClearButton)
        passwordClearButton = findViewById(R.id.passwordClearButton)
    }

    private fun setupSpinner() {
        val items = arrayOf("Manager", "Supplier")
        val adapter = object : ArrayAdapter<String>(this, R.layout.spinner_item, items) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.text = if (userTypeSpinner.selectedItemPosition == AdapterView.INVALID_POSITION) {
                    "Select your role"
                } else {
                    getItem(position)
                }
                return view
            }
        }

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        userTypeSpinner.adapter = adapter
        userTypeSpinner.setSelection(AdapterView.INVALID_POSITION)
    }

    private fun setupClearButtons() {
        emailClearButton.setOnClickListener { emailEditText.text.clear() }
        passwordClearButton.setOnClickListener { passwordEditText.text.clear() }

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                emailClearButton.visibility = if (s?.isNotEmpty() == true) View.VISIBLE else View.GONE
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                passwordClearButton.visibility = if (s?.isNotEmpty() == true) View.VISIBLE else View.GONE
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupLoginButton() {
        loginButton.setOnClickListener {
            val selectedRole = userTypeSpinner.selectedItem?.toString()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            when {
                selectedRole == null -> showToast("Please select your role")
                email.isEmpty() -> showToast("Please enter email")
                password.isEmpty() -> showToast("Please enter password")
                else -> performLogin(email, password, selectedRole)
            }
        }
    }

    private fun performLogin(email: String, password: String, userType: String) {
        when (userType) {
            "Manager" -> {
                if (email == "managertest@gmail.com" && password == "password123") {
                    navigateToUserScreen(userType)
                } else {
                    showToast("Invalid Manager credentials")
                }
            }
            "Supplier" -> {
                if (email == "SupplierY@gmail.com" && password == "Supplier_123qq") {
                    navigateToUserScreen(userType)
                } else {
                    showToast("Invalid Supplier credentials")
                }
            }
        }
    }

    private fun navigateToUserScreen(userType: String) {
        val intent = when (userType) {
            "Supplier" -> Intent(this, SupplierActivity::class.java)
            "Manager" -> Intent(this, ManagerActivity::class.java)
            else -> null
        }
        intent?.let {
            startActivity(it)
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}