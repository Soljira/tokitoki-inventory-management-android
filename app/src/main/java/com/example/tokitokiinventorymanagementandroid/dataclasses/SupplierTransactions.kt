package com.example.tokitokiinventorymanagementandroid.dataclasses

import com.google.firebase.Timestamp

data class SupplierTransactions(
    val transactionID: String = "",
    val dateTimeOfArrival: Timestamp? = null,  //IDK PANO TO I-INITIALIZE SO FOR NOW USE NULL BUT RESEARCH HOW TO INITIALIZE IT PROPERLY
    val dateTimeOrdered: Timestamp? = null,
    val items: MutableList<InventoryItem> = mutableListOf(),
    val managerID: String = "",
    val supplierID: String = "",
    val status: String = "",  // either pending, delivered, or cancelled
    val totalCost: Double = 0.0
)
