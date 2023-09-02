package com.example.shebetar.ProfileScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shebetar.Classes.User.User
import com.example.shebetar.DataBase.getUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun ProfileScreen(){
    var user = User()
    runBlocking{launch{user = getUser()
        }}
    Log.d("Profile", user.toString())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Name: ${user.firstName}")
    }
    
}