package com.example.shebetar.RegisterLoginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shebetar.TopNavBar.TopNavBar

@Composable
fun RegisterLoginScreen(navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopNavBar()
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth().padding(10.dp),
            shape = CircleShape,
            onClick = { navController.navigate("login") }) {
            Text(text = "Login")
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth().padding(10.dp),
            shape = CircleShape,
            onClick = { navController.navigate("register") }) {
            Text(text = "Register")
        }
    }
}