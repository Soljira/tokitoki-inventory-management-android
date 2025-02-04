package com.example.tokitokiinventorymanagementandroid.manager.settings

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tokitokiinventorymanagementandroid.R

class ManagerSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_account)

        val languageOption: LinearLayout = findViewById(R.id.language_option)
        languageOption.setOnClickListener { view ->
            showPopupMenu(
                view,
                R.menu.language_menu,
                mapOf(
                    R.id.language_english to "Selected: English",
                    R.id.language_filipino to "Selected: Filipino"
                )
            )
        }
        val branchOption: LinearLayout = findViewById(R.id.branch_option)
        branchOption.setOnClickListener { view ->
            showPopupMenu(
                view,
                R.menu.branch_menu,
                mapOf(
                    R.id.branch_urdneta to "Selected: SM City Urdaneta Central",
                    R.id.branch_dagupan to "Selected: CSI City Mall Dagupan",
                    R.id.branch_launion to "Selected: CSI City Mall La Union"
                )
            )
        }
        val editProfileButton: Button = findViewById(R.id.edit_profile_button)
        editProfileButton.setOnClickListener {
            val editProfileBottomSheet = EditProfileBottomSheet()
            editProfileBottomSheet.show(supportFragmentManager, "EditProfileBottomSheet")
        }
        val reportBugButton: LinearLayout = findViewById(R.id.report_bug_button)
        reportBugButton.setOnClickListener {
            val reportBugBottomSheet = ReportBugBottomSheet()
            reportBugBottomSheet.show(supportFragmentManager, "ReportBugBottomSheet")
        }
        val logoutButton: Button = findViewById(R.id.btnLogout)
        logoutButton.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun showPopupMenu(view: View, menuResId: Int, messages: Map<Int, String>) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(menuResId, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            messages[menuItem.itemId]?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                true
            } ?: false
        }
        popupMenu.show()
    }

    private fun showLogoutConfirmation() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_logout, null)

        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomDialog)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        alertDialog.window?.setLayout((resources.displayMetrics.widthPixels * 0.8).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        val btnLogout = dialogView.findViewById<Button>(R.id.btn_logout)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

}