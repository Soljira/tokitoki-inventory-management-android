package com.example.tokitokiinventorymanagementandroid.helpers
import android.app.Activity
import android.content.Intent
import com.example.tokitokiinventorymanagementandroid.R
import com.example.tokitokiinventorymanagementandroid.manager.home.HomeActivity
import com.example.tokitokiinventorymanagementandroid.manager.stock.StockActivity
import com.example.tokitokiinventorymanagementandroid.manager.orders.OrdersActivity
import com.example.tokitokiinventorymanagementandroid.manager.supplier.SupplierActivity
import com.example.tokitokiinventorymanagementandroid.manager.recipes.RecipeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

/*
    HELPER CLASS SO WE CAN REUSE THIS CODE YES
 */

object BottomNavigationInitialization {

    fun setupBottomNavigation(activity: Activity, bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navbar_manager_home -> {
                    if (activity !is HomeActivity) {
                        activity.startActivity(Intent(activity, HomeActivity::class.java))
                    }
                    true
                }
                R.id.navbar_manager_stock -> {
                    if (activity !is StockActivity) {
                        activity.startActivity(Intent(activity, StockActivity::class.java))
                    }
                    true
                }
                R.id.navbar_manager_orders -> {
                    if (activity !is OrdersActivity) {
                        activity.startActivity(Intent(activity, OrdersActivity::class.java))
                    }
                    true
                }
                R.id.navbar_manager_recipe -> {
                    if (activity !is RecipeActivity) {
                        activity.startActivity(Intent(activity, RecipeActivity::class.java))
                    }
                    true
                }
                R.id.navbar_manager_supplier -> {
                    if (activity !is SupplierActivity) {
                        activity.startActivity(Intent(activity, SupplierActivity::class.java))
                    }
                    true
                }
                else -> false
            }
        }
    }
}