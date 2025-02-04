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
import android.app.Dialog
import android.view.Gravity
import android.view.Window
import android.view.ViewGroup
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.myapplication.SupplierProcessConfirmedActivity


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

class Popups : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.supplier_process_transaction)


        val showPopupButton = findViewById<Button>(R.id.remove_request_button)
        showPopupButton.setOnClickListener {
            showRequestPopup()
        }

        val showPopupButton2 = findViewById<Button>(R.id.deliver_request_button)
        showPopupButton2.setOnClickListener {
            showDeliverRequestPopup()
        }
    }


    private fun showRequestPopup() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.remove_request_popup)
        dialog.setCancelable(true)

        dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setGravity(Gravity.CENTER)


        val removeButton = dialog.findViewById<Button>(R.id.btn_remove)
        val cancelButton = dialog.findViewById<Button>(R.id.btn_cancel)

        removeButton.setOnClickListener {
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showDeliverRequestPopup() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.deliver_request_popup)
        dialog.setCancelable(true)

        dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setGravity(Gravity.CENTER)

        val deliverButton = dialog.findViewById<Button>(R.id.btn_deliver)
        val cancel2Button = dialog.findViewById<Button>(R.id.btn_cancel2)

        deliverButton.setOnClickListener {
            Log.d("MainActivity", "Deliver button clicked!")
            val intent = Intent(this@Popups, SupplierProcessConfirmedActivity::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        cancel2Button.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}


