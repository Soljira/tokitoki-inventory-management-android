//package com.example.tokitokiinventorymanagementandroid.helpers
//
//
//import android.content.Context
//import android.util.Log
////import com.example.studenthandbookapp.dataclasses.Event
////import com.example.studenthandbookapp.dataclasses.StudentManual
//import com.google.firebase.Timestamp
//import com.google.firebase.firestore.FirebaseFirestore
//import java.io.BufferedReader
//import java.io.InputStreamReader
//import java.text.SimpleDateFormat
//import java.util.Locale
//
//// chatgpt'd this entire stuff kasi i dont have time to learn/remember how Readers work
//object AddDataToFirestore {
//
//    private const val TAG = "AddDataToFirestore"
//
//    /**
//    HOW TO USE
//    MAKE SURE NAKALAGAY SA assets folder UNG TXT FILE OTHERWISE DI TO GAGANA
//
//    In another activity:
//    AddDataToFirestore.addEventsFromFile(this, "holidays_events.txt")
//
//    replace holiday_events with your file of choice
//    note: every time you run that activity, madadagdagan ung data sa firestore, so make sure alisin agad ung
//    code (SA ACTIVITY MO, DON'T TOUCH THIS FILE) para hindi ma-overpopulate ung firestore
//    note: change "events_test" to the collection of your choice
//    either "events_test", "events_school", "events_holiday", or "events_user"
//     */
//    fun addEventsFromFile(context: Context, fileName: String, collectionName : String) {
//        val db = FirebaseFirestore.getInstance()
//        val assetsList = context.assets.list("")?.joinToString(", ") ?: "No files"
//        Log.d(TAG, "Assets folder contains: $assetsList")
//
//
//        try {
//            val inputStream = context.assets.open(fileName)
//            val reader = BufferedReader(InputStreamReader(inputStream))
//
//            reader.forEachLine { line ->
//                val components = line.split(",")
//
//                if (components.size >= 4) {
//                    val title = components[0].trim()
//                    val dateString = components[1].trim()
//                    val description = components[2].trim()
//                    val location = components[3].trim()
//
//                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
//                    val date = dateFormat.parse(dateString)
//                    val timestamp = date?.let { Timestamp(it) }
//
//                    val event = Event(
//                        title = title,
//                        date = timestamp,
//                        description = description,
//                        location = location
//                    )
//
//                    db.collection(collectionName)
//                        .add(event)
//                        .addOnSuccessListener { documentReference ->
//                            Log.d(TAG, "Event added with ID: ${documentReference.id}")
//                        }
//                        .addOnFailureListener { e ->
//                            Log.e(TAG, "Error adding event: ${e.message}")
//                        }
//                } else {
//                    Log.w(TAG, "Invalid line format: $line")
//                }
//            }
//
//        } catch (e: Exception) {
//            Log.e(TAG, "Error reading file: ${e.message}")
//        }
//    }
//
//
//
//
//    fun addStudentManualFromFile(context: Context, fileName: String, collectionName: String = "manual") {
//        val db = FirebaseFirestore.getInstance()
//        val assetsList = context.assets.list("")?.joinToString(", ") ?: "No files"
//        Log.d(TAG, "Assets folder contains: $assetsList")
//
//        try {
//            val inputStream = context.assets.open(fileName)
//            val reader = BufferedReader(InputStreamReader(inputStream))
//
//            reader.forEachLine { line ->
//                // Regex to match: Heading,Subheading,"Content with, commas",PageNum
//                val regex = """([^,]+),([^,]+),"([^"]+)",(\d+)""".toRegex()
//                val matchResult = regex.find(line)
//
//                if (matchResult != null) {
//                    val (heading, subheading, content, pageNumStr) = matchResult.destructured
//                    val pageNum = pageNumStr.toIntOrNull() ?: -1
//
//                    if (pageNum >= 0) {
//                        val bookPage = StudentManual(
//                            heading = heading.trim(),
//                            subheading = subheading.trim(),
//                            content = content.trim().replace("\\n", "\n"), // Apply actual newlines
//                            pageNum = pageNum
//                        )
//
//                        db.collection(collectionName)
//                            .add(bookPage)
//                            .addOnSuccessListener { documentReference ->
//                                Log.d(TAG, "Book page added with ID: ${documentReference.id}")
//                            }
//                            .addOnFailureListener { e ->
//                                Log.e(TAG, "Error adding book page: ${e.message}")
//                            }
//                    } else {
//                        Log.w(TAG, "Invalid page number in line: $line")
//                    }
//                } else {
//                    Log.w(TAG, "Invalid line format: $line")
//                }
//            }
//
//        } catch (e: Exception) {
//            Log.e(TAG, "Error reading file: ${e.message}")
//        }
//    }
//
//
//
//
//}
