package com.example.tokitokiinventorymanagementandroid.dataclasses

import com.google.firebase.Timestamp


data class Recipe(
    //should have the same fields as the ones in Firestore
    val recipeID: String = "",
    val name: String = "",
//    * Categories:
//    - Appetizers
//    - Salads
//    - Soups
//    - Main Courses: Meat dishes
//    - Main Courses: Seafood
//    - Main Courses: Vegetarian options
//    - Pasta
//    - Pizzas
//    - Sides
//    - Desserts
//    - Beverages
    val category: String = "",
    val ingredients: MutableList<String> = mutableListOf(),
    val mealImage: String = ""
)
