//package com.example.tokitokiinventorymanagementandroid.helpers
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.widget.Toast
//import com.example.tokitokiinventorymanagementandroid.dataclasses.
//import com.google.firebase.Timestamp
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.DocumentSnapshot
//import java.text.SimpleDateFormat
//import java.util.Locale
//import java.util.Date
//import java.util.*
//
//// ALL FIREBASE-RELATED REUSABLE CODE IN ONE PLACE BERI NICE
//object FirestoreFunctions {
//
//    /**
//     * General Utility
//     */
//
//
//    /**
//     * Retrieves all documents from a specified Firestore collection
//     * i.e. nirereturn nya ung a list of all documents in a given collection
//     * @param collectionName The name of the collection to retrieve documents from
//     * @param onComplete Callback function that receives the list of objects converted to the specified type
//     * @return List<T>
//     *
//     *     HOW TO USE
//    // Example usage for Event objects (you can change Event with other stuff)
//    FirestoreFunctions.getAllDocumentsFromCollection("events_school", Event::class.java) { eventsList ->
//    if (eventsList != null) {
//    // Process the list of events
//    for (event in eventsList) {
//    println("Event title: ${event.title}")
//    }
//    } else {
//    println("Failed to retrieve events or collection doesn't exist")
//    }
//    }
//
//
//    // FOR DEBUGGING PURPOSES
//    FirestoreFunctions.getAllDocumentsFromCollection(eventType, Event::class.java) { eventsList ->
//    if (eventsList != null) {
//    println("Retrieved ${eventsList.size} events from Firestore")
//
//    eventsList.forEachIndexed { index, event ->
//    println("Event #${index + 1} - Title: ${event.title}, Date: ${event.date}")
//    }
//    } else {
//    println("Failed to retrieve events or collection doesn't exist")
//    }
//    }
//     */
//
//
//    fun <T> getAllDocumentsFromCollection(
//        collectionName: String,
//        objectClass: Class<T>,
//        onComplete: (List<T>?) -> Unit
//    ) {
//        val db = FirebaseFirestore.getInstance()
//
//        db.collection(collectionName)
//            .get()
//            .addOnSuccessListener { querySnapshot ->
//                if (!querySnapshot.isEmpty) {
//                    val documentList = querySnapshot.documents.mapNotNull { document ->
//                        document.toObject(objectClass)
//                    }
//                    onComplete(documentList)
//                } else {
//                    // Collection exists PERO WALANG LAMAN
//                    onComplete(emptyList())
//                }
//            }
//            .addOnFailureListener { e ->
//                println("Error fetching documents from $collectionName: ${e.message}")
//                onComplete(null)
//            }
//    }
//
//
//    /**
//     * Retrieves all documents with their IDs from a specified Firestore collection
//     * @param collectionName The name of the collection to retrieve documents from
//     * @param objectClass The class to convert documents to
//     * @param onComplete Callback function that receives pairs of document IDs and converted objects
//     *
//     * HOW TO RETRIEVE ID FROM THE RETURNED OBJECT
//    FirestoreFunctions.getAllDocumentsWithIds("events_school", Event::class.java) { eventPairsList ->
//    if (eventPairsList != null) {
//    for (pair in eventPairsList) {
//    val documentId = pair.first  // This is the document ID
//    val event = pair.second      // This is the Event object
//
//    println("Document ID: $documentId")
//    println("Event title: ${event.title}")
//
//    // You can use the ID and object separately
//    showEventDetails(documentId, event)
//    }
//    } else {
//    println("Failed to retrieve events or collection doesn't exist")
//    }
//    }
//
//    If you need to create a list of just the IDs, you can map the results:
//    FirestoreFunctions.getAllDocumentsWithIds("events_school", Event::class.java) { eventPairsList ->
//    if (eventPairsList != null) {
//    // Extract just the document IDs into a separate list
//    val allDocumentIds = eventPairsList.map { it.first }
//
//    // Extract just the Event objects into a separate list
//    val allEvents = eventPairsList.map { it.second }
//
//    // Do something with the lists
//    println("All document IDs: $allDocumentIds")
//    }
//    }
//     */
//    fun <T> getAllDocumentsWithIds(
//        collectionName: String,
//        objectClass: Class<T>,
//        onComplete: (List<Pair<String, T>>?) -> Unit
//    ) {
//        val db = FirebaseFirestore.getInstance()
//
//        db.collection(collectionName)
//            .get()
//            .addOnSuccessListener { querySnapshot ->
//                if (!querySnapshot.isEmpty) {
//                    val documentList = querySnapshot.documents.mapNotNull { document ->
//                        val obj = document.toObject(objectClass)
//                        if (obj != null) Pair(document.id, obj) else null
//                    }
//                    onComplete(documentList)
//                } else {
//                    // Collection exists but is empty
//                    onComplete(emptyList())
//                }
//            }
//            .addOnFailureListener { e ->
//                println("Error fetching documents from $collectionName: ${e.message}")
//                onComplete(null)
//            }
//    }
//
//    /**
//     * Events functions
//     */
//
//    // Code for saving custom event details to Firestore; not sure kung magagamit to given the time pero lagay ko lang here just in case
//    // eventType is either
//    // - events_user
//    // - events_school
//    // - events_holidays
//    /**
//    HOW TO USE
//
//     */
//    fun saveEventToFirestore(context: Context, eventType: String, event: Event) {
//        val db = FirebaseFirestore.getInstance()
//        db.collection(eventType)
//            .add(event)
//            .addOnSuccessListener { documentReference ->
//                Toast.makeText(
//                    context,
//                    "Event added with ID: ${documentReference.id}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//            .addOnFailureListener { e ->
//                Toast.makeText(context, "Error adding event: ${e.message}", Toast.LENGTH_SHORT)
//                    .show()
//            }
//    }
//
//    // Code for GETTING event details from Firestore
//    // This will return an Event data class (using ung ginawa ko kanina na Event object)
//    // ung paggawa ng list of Events siguro later na
//    // eventType is either
//    // - events_user
//    // - events_school
//    // - events_holidays
//    fun getEventById(eventType: String, eventId: String, onComplete: (Event?) -> Unit) {
//        val db = FirebaseFirestore.getInstance()
//
//        db.collection(eventType).document(eventId)
//            .get()
//            .addOnSuccessListener { document: DocumentSnapshot ->
//                if (document.exists()) {
//                    val event = document.toObject(Event::class.java)
//                    onComplete(event) // returns the Event object
//                } else {
//                    onComplete(null) // No document found
//                }
//            }
//            .addOnFailureListener { e ->
//                println("Error fetching event: ${e.message}")
//                onComplete(null) // In case of failure
//            }
//    }
//
//    // This will set an Event object to the data from Firestore
//    // i found out that firestore is asynchronous, so habang nagfefetch pa ng data, narurun na ung ibang code, so
//    // nagrereturn sya ng blank test minsan kasi hindi pa tapos magfetch ng data
//    // eventType is either
//    // - events_user
//    // - events_school
//    // - events_holidays
//    /*
//        HOW TO USE SA ACTIVIITIES:
//        FirestoreFunctions.setEventObjectById("events_school", eventId) { event ->
//            if (event != null) {
//                eventTest = event  // eventTest is your empty Event object
//            } else {
//                println("Event not found.")
//            }
//        }
//
//        OR (SINCE ERROR CHECK IS ALREADY HANDLED HERE)
//
//        FirestoreFunctions.setEventObjectById("events_school", eventId) { event ->
//            eventTest = event  // eventTest is your empty Event object
//            title.text = eventTest?.title
//        }
//
//     */
//    fun setEventObjectById(eventType: String, eventId: String, onComplete: (Event?) -> Unit) {
//        getEventById(eventType, eventId) { event ->
//            if (event != null) {
//                onComplete(event) // returns the Event object
//            } else {
//                println("Event not found or an error occurred.")
//                onComplete(null)
//            }
//        }
//    }
//
//    // Converts timestamp (from firestore) to String; THIS SHOULD RETURN A STRING
//    /*
//        HOW TO USE (assuming `date` is a textview)
//        FirestoreFunctions.getEventById("event_school", eventId) { event ->
//            if (event != null) {
//                val formattedDate = FirestoreFunctions.formatTimestampToDateString(event.date)
//                date.text = formattedDate
//            } else {
//                println("Event not found.")
//            }
//        }
//     */
//    @SuppressLint("SimpleDateFormat")
//    fun formatTimestampToDateString(timestamp: Timestamp?): String {
//        return if (timestamp != null) {
//            val sdf = SimpleDateFormat("MMM dd, yyyy - hh:mm a", Locale.getDefault())
//            val date = timestamp.toDate()
//            sdf.format(date)
//        } else {
//            "No date available"
//        }
//    }
//
//    /**
//     * FROM OFFICIAL FIREBASE DOCS
//     */
//    fun deleteEvent(eventType: String, eventId: String, callback: (Boolean) -> Unit) {
//        val db = FirebaseFirestore.getInstance()
//        db.collection(eventType).document(eventId)
//            .delete()
//            .addOnSuccessListener {
//                callback(true)
//            }
//            .addOnFailureListener { e ->
//                e.printStackTrace()
//                callback(false)
//            }
//    }
//
//
//
//    // fancy divider lol
//    /***********************************************
//     ***********************************************
//     ***********************************************
//     ***********************************************/
//
//    /**
//     * Calendar functions
//     */
//
//    /*fun checkandNotifyTodayEvents(context:Context) {
//        val today=SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())//Get today's date as a string
//        val eventType = "events_school"
//
//        FirestoreFunctions.getAllDocumentsFromCollection(eventType, Event::class.java) { eventsList ->
//            if (eventsList != null) {
//                val todayEvents = eventsList.filter { event ->
//                    val eventDate = (event.date as? Timestamp)?.toDate()?.let { formatTimestampToDateString (it) }
//
//                    eventDate == today
//                }
//
//                if(todayEvents.isNotEmpty()){
//                    val eventDetails = todayEvents.joinToString("\n") { it.title}
//                    println("Today's Events: $eventDetails")
//                } else {
//                    println("No events today.")
//                }
//            } else {
//                println("Failed to retrieve events or collection doesn't exist.")
//            }
//        }
//    }
//
//
//    I KENAT may  exam pa kami ng 9 -GAB
//*/
//    fun listenForEventChanges(eventType: String, onEventUpdate: (List<Event>) -> Unit) {
//        val db = FirebaseFirestore.getInstance()
//
//        db.collection(eventType)
//            .addSnapshotListener { snapshot, error ->
//                if (error != null) {
//                    println("Error listening for event changes: ${error.message}")
//                    return@addSnapshotListener
//                }
//                if (snapshot != null && !snapshot.isEmpty) {
//                    val events = snapshot.documents.mapNotNull { it.toObject(Event::class.java) }
//                    onEventUpdate(events) // Notify the calendar of the updated events
//                }
//            }
//    }
//}
