package com.example.shebetar.DataBase

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager

var TAG = "DataBase"
fun open_connection(){
    val url = "jdbc:postgresql://ep-cold-bird-43781123.eu-central-1.aws.neon.tech/neondb?user=LeftTry&password=8kgIdsS5ZEJH" // Replace with your database URL
    val username = "cwnhmmhf" // Replace with your database username
    val password = "aLbWCIAA5UEzqWZ9d_tIPbk0xAVpt6qx" // Replace with your database password

    var connection: Connection? = null

    try {
        // Register the MySQL driver
        Class.forName("org.postgresql.Driver")

        // Create a connection to the database
        connection = DriverManager.getConnection(url)

        // Connection successful, perf
        Log.v(TAG, "connection opened")
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        // Close the connection
        connection?.close()
    }
}