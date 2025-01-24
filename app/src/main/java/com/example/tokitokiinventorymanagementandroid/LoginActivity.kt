package com.example.tokitokiinventorymanagementandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var userTypeSpinner: Spinner
    private lateinit var loginButton: Button
    private lateinit var emailClearButton: ImageView
    private lateinit var passwordClearButton: ImageView
    private lateinit var progressBar: ProgressBar

    private lateinit var intentManagerHome: Intent
    private lateinit var intentSupplierHome: Intent

    private lateinit var auth: FirebaseAuth

    lateinit var spinnerLoginType : Spinner

    // TODO: Fix Spinner not being able to pick Manager unless Supplier is picked first
    // TODO: Add show password
    public override fun onStart() {
        super.onStart()
        auth = Firebase.auth

        /**
         * Persistent session functionality
         */
        // Checks if a user is already signed in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.d("Auth", "User is signed in: ${currentUser.email}")

            val uid = currentUser.uid
            Log.d("Auth", "User ID: $uid")
            val db = Firebase.firestore

            // Gets user's role from Firestore
            // Note to self: uid in Firestore must be the same uid in Firebase Authentication for
            // this code snippet to work
            // Maybe add a registration page (application type where the admin has to approve it)
            // to lessen the hassle of manually setting up an account
            db.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    val role = document?.getString("role")
                    if (role != null) {
                        Log.d("Firestore", "Navigating to $role screen")
                        navigateToUserScreen(role)
                    } else {
                        Log.e("Firestore", "Role is missing or document doesn't exist")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Error fetching user role: ${exception.message}")
                }
        } else {
            Log.d("Auth", "No user is signed in. Staying on login screen.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intentManagerHome = Intent(this,
            com.example.tokitokiinventorymanagementandroid.manager.home.HomeActivity
            ::class.java)
        intentSupplierHome = Intent(this,
            com.example.tokitokiinventorymanagementandroid.supplier.home.HomeActivity
            ::class.java)

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
        progressBar = findViewById(R.id.progressBar)
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
            progressBar.visibility = View.VISIBLE
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

    @SuppressLint("RestrictedApi")
    private fun performLogin(email: String, password: String, userType: String) {
        hideKeyboard(currentFocus ?: View(this))
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    showToast("Login Successful")
                    navigateToUserScreen(userType) // Goes to the Main Activity according to user type
                    finish()
                } else {
                    showToast("Authentication failed.")
                }
            }
    }

    private fun navigateToUserScreen(userType: String) {
        val intent = when (userType) {
            "Supplier" -> intentSupplierHome
            "Manager" -> intentManagerHome
            else -> null
        }
        intent?.let {
            println("RANKINE")
            startActivity(it)
            finish()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}