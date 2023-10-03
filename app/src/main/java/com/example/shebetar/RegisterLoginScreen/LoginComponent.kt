package com.example.shebetar.RegisterLoginScreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shebetar.Classes.User.User
import com.example.shebetar.DataBase.getUserByEmail
import com.example.shebetar.DataBase.loginDevice
import com.example.shebetar.TopNavBar.TopNavBar
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun LoginComponent(navController: NavHostController, context: Context){
    TopNavBar()
    // State variables for email and password
    val emailPhoneState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Email input field
        TextField(
            value = emailPhoneState.value,
            onValueChange = { emailPhoneState.value = it },
            label = { Text("Email or Phone") }
        )

        // Password input field
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        // Login button
        Button(
            onClick = { val user = performLogin(context, emailPhoneState.value, passwordState.value)
                        loginDevice(user)
                        navController.navigate("home")},
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Log In")
        }
    }
}

private fun performLogin(context: Context, emailPhone: String, password: String): User {
    var user = User()
    runBlocking { launch{ user = getUserByEmail(context, emailPhone, password) } }
    return user
}