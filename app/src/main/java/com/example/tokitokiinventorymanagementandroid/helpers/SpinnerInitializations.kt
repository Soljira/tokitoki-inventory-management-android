package com.example.tokitokiinventorymanagementandroid.helpers

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.tokitokiinventorymanagementandroid.R

/*
    HELPER CLASS SO WE CAN REUSE STUFF AND KEEP THINGS CLEAN IN THE MAIN FILES
 */
object SpinnerInitializations {

    // TODO: Make this scalable to other types of spinners as well

    // Handles the spinner in the login page
    fun loginSpinner(activity : Activity, spinner : Spinner) {
        ArrayAdapter.createFromResource(
            activity,
            R.array.roles,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            // pre-selects manager role
            spinner.setSelection(0)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {

                Toast.makeText(activity,
                    "Selected role is ${spinner.selectedItem}",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}