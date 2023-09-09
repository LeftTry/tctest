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
val MODEL: String
= android.os.Build.MODEL

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
    Log.d("DataBaseAddUserMethod","Last added document: $data")
    user.id = data?.get("id").toString().toLong() + 1
    db.collection("users").document(user.id.toString())
        .set(user.toMap())
        .addOnSuccessListener {
            Log.d("DataBaseAddUserMethod", "User added with id: ${user.id}")
        }
        .addOnFailureListener { e ->
            Log.w("DataBaseAddUserMethod", "Error adding user", e)
        }
}
suspend fun updateUser(user: User){
    db.collection("users").document(user.id.toString()).update(user.toMap())
        .addOnSuccessListener{
            Log.d("DataBaseUpdateUserMethod", "success")
        }
        .addOnFailureListener{
            Log.d("DataBaseUpdateUserMethod", "failure")
        }
        .await()
}

suspend fun getUserByDevice(): User {
    var query = db.collection("LoginedDevices").document(MODEL)
        .get()
        .addOnSuccessListener {
            Log.d("DataBaseUserGetMethod", "found")
        }
        .addOnFailureListener { Log.d("DataBaseUserGetMethod", "not found")}
        .await()
    Log.d("QueryGetUserMethod", query.toString())
    val id = query.get("userId")
    Log.d("IDGetUserMethod", id.toString())
    query = db.collection("users").document(id.toString())
        .get()
        .addOnSuccessListener {
            Log.d("DataBaseUserGetMethod", "found")
        }
        .addOnFailureListener { Log.d("DataBaseUserGetMethod", "not found")}
        .await()
    val user = User()
    user.toUserFromDocumentSnapshot(query)
    return user
}

suspend fun getUserByEmailOrPhone(emailPhone: String, password: String): User{
    var query: QuerySnapshot? = null
        Log.d("DataBaseGetUserByEmailOrPhoneMethod", "email found")
        query = db.collection("users").whereEqualTo("email", emailPhone)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { Log.d("DataBaseGetUserByEmailOrPhoneMethod", "User found") }
            .addOnFailureListener { Log.d("DataBaseGetUserByEmailOrPhoneMethod", "User not found") }
            .await()
    if(emailPhone.contains("0-9+".toRegex())) {
        query = db.collection("users").whereEqualTo("phone", emailPhone)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { Log.d("DataBaseGetUserByEmailOrPhoneMethod", "User found") }
            .addOnFailureListener { Log.d("DataBaseGetUserByEmailOrPhoneMethod", "User not found") }
            .await()
    }
    val user = User()
    user.toUserFromQuerySnapshot(query)
    Log.d("DataBaseGetUserByEmailOrPhoneMethod", user.toString())
    return user
}
suspend fun deleteUser(user: User){
    db.collection("users").document(user.id.toString()).delete()
        .addOnSuccessListener {
            Log.d("DataBaseDeleteUserMethod", "user deleted")
        }
        .addOnFailureListener{
            Log.d("DataBaseDeleteUserMethod", "user is not deleted")
        }
        .await()
}

suspend fun isDeviceLogined(): Boolean {
    // Defining a query to check if device has already logined
    val query = db.collection("LoginedDevices").whereEqualTo("name", MODEL)
        .get()
        .addOnSuccessListener {
            Log.d("DataBaseIsDeviceLoginedMethod", "found")
        }
        .addOnFailureListener { Log.d("DataBaseIsDeviceLoginedMethod", "not found")}
        .await()
    Log.d("Query", query.isEmpty.toString())
    // if query is empty -> device is not logined
    return !query.isEmpty
}

fun loginDevice(user: User){
    val device: HashMap<String, String> = hashMapOf(
        "name" to MODEL,
        "userId" to user.id.toString()
    )

    // Adding new device to the database of logined devices
    db.collection("LoginedDevices").document(MODEL).set(device).addOnSuccessListener {
        Log.d("DataBaseLoginDeviceMethod", "Device logined")
    }
        .addOnFailureListener { e ->
            Log.w("DataBaseLoginDeviceMethod", "Error adding device", e)
        }
}

fun logoutDevice(){
    db.collection("LoginedDevices").document(MODEL).delete().addOnSuccessListener {
        Log.d("DataBaseLogoutDeviceMethod", "Device logined")
    }
        .addOnFailureListener { e ->
            Log.w("DataBaseLogoutDeviceMethod", "Error adding device", e)
        }
}