package com.example.shebetar.RegisterLoginScreen

import androidx.compose.foundation.layout.Box
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
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TopNavBar()
        Button(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(bottom = 150.dp, start = 10.dp, end = 10.dp),
            shape = CircleShape,
            onClick = { navController.navigate("login") }) {
            Text(text = "Login")
        }
        Button(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 200.dp),
            shape = CircleShape,
            onClick = { navController.navigate("register") }) {
            Text(text = "Register")
        }
    }
}