package com.example.shebetar.DataBase

import android.annotation.SuppressLint
import android.util.Log
import com.example.shebetar.Classes.User.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

@SuppressLint("StaticFieldLeak")

//Database initialization
val db = Firebase.firestore

// Write data to the database
suspend fun addUser(user: User){
    // Defining a query to find the last added document
    val query = db.collection("users").orderBy("dateOfJoin", Query.Direction.DESCENDING).limit(1)

    // Executing the query and getting the results
    val querySnapshot: QuerySnapshot = query.get().await()

    // Getting the last document from the results
    val lastDocument: DocumentSnapshot? = querySnapshot.documents.first()

    // Getting the data from the last document
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

suspend fun isDeviceLogined(): DocumentSnapshot? {
    // Defining a query to check if device has already logined
    val query = db.collection("LoginedDevices").document(android.os.Build.MODEL)

    // Returning null or DocumentSnapshot
    return query.get().await()
}