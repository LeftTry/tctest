package com.example.shebetar.DataBase

import android.annotation.SuppressLint
import android.util.Log
import com.example.shebetar.User.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// Initialize Firebase Realtime Database in your Application class
val database: FirebaseDatabase = FirebaseDatabase.getInstance()

// Get a reference to the root of the database
val rootRef = database.reference

// Get a reference to a specific child of the database
val usersRef = rootRef.child("users")
@SuppressLint("RestrictedApi")
fun openConnection(){
    val db = Firebase.firestore
    // Create a new user with a first and last name

    val user = User()

// Add a new document with a generated ID
    db.collection("users").document(user.id.toString())
        .set(user.toMap())
        .addOnSuccessListener { documentReference ->
            Log.d("TAG", "DocumentSnapshot added with ID: ")
        }
        .addOnFailureListener { e ->
            Log.w("TAG", "Error adding document", e)
        }
    /*
    // Listen for changes to the "users" child
    usersRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Handle changes to the "users" child
            for (ds in dataSnapshot.children) {
                val firstName = ds.child("firstName").getValue(String::class.java)
                val lastName = ds.child("lastName").getValue(String::class.java)
                val email = ds.child("email").getValue(String::class.java)
                val city = ds.child("city").getValue(String::class.java)
                Log.d("DATABASE", "$firstName / $lastName / $email / $city")
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle database errors
        }
    })
     */
}

// Write data to the database
fun writeData(user: User){
    usersRef.orderByKey()
    val userRef = database.getReference("User" + user.id)
    userRef.setValue(user)
}
