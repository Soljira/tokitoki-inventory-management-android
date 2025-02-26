package com.example.tokitokiinventorymanagementandroid.dataclasses

import com.google.firebase.Timestamp

// USE IN unitOfMeasure; add more as needed
enum class UnitOfMeasure {
    GRAMS,
    KILOGRAMS,
    LITERS,
    MILLILITERS,
    PIECES,
    UNITS,
}

// important! in data classes, use val!
data class InventoryItem(
    val itemID: String = "",
    val productName: String = "",
    val supplierID: String = "",
    val quantity: Double = 0.0, // i used double because the unit of measure can be defined
    val unitOfMeasure: String = "",
    val usedInRecipes: MutableList<Recipe> = mutableListOf(),
    val expiryDate: Timestamp? = null,  //IDK PANO TO I-INITIALIZE SO FOR NOW USE NULL BUT RESEARCH HOW TO INITIALIZE IT PROPERLY
)
