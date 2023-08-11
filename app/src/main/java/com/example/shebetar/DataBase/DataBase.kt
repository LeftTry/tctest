package com.example.shebetar.DataBase

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

var database: FirebaseDatabase = TODO()

@SuppressLint("RestrictedApi")
fun openConnection(){
    // Initialize Firebase Realtime Database in your Application class
    database = FirebaseDatabase.getInstance()

    // Get a reference to the root of the database
    val rootRef = database.reference

    // Get a reference to a specific child of the database
    val usersRef = rootRef.child("users")

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

    // Write data to the "users" child
    val user = User("John", "Doe", "johndoe@example.com")
    val userRef = usersRef.push()
    userRef.setValue(user)

    // Update data in the "users" child
    val newUser = User("John", "Doe", "johndoe@example.com", "New York")
    userRef.setValue(newUser)
}

// User class
data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val city: String? = null
){
    constructor() : this("", "", "")
}