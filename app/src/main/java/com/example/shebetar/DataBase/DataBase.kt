package com.example.shebetar.DataBase

import android.annotation.SuppressLint
import android.util.Log
import com.example.shebetar.User.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore

// Write data to the database
suspend fun addUser(user: User){
    // Define a query to find the last added document
    val query = db.collection("users").orderBy("dateOfJoin", Query.Direction.DESCENDING).limit(1)

    // Execute the query and get the results
    val querySnapshot: QuerySnapshot = query.get().await()

    // Get the last document from the results
    val lastDocument: DocumentSnapshot? = querySnapshot.documents.first()

    // Get the data from the last document
    val data = lastDocument?.data


    // Do something with the data
    Log.d("DataBase","Last added document: $data")
    user.id = data?.get("id").toString().toInt() + 1
    db.collection("users").document(user.id.toString())
        .set(user.toMap())
        .addOnSuccessListener {
            Log.d("TAG", "DocumentSnapshot added with ID: ")
        }
        .addOnFailureListener { e ->
            Log.w("TAG", "Error adding document", e)
        }
}
