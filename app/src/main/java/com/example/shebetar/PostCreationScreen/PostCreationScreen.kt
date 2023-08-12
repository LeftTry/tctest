package com.example.shebetar.PostCreationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shebetar.HomeScreen.createPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreationScreen(navController: NavHostController){
    val text = remember { mutableStateOf("") }
    IconButton(
        modifier = Modifier
            .background(shape = RoundedCornerShape(50), color = Color.Transparent)
            .padding(start = 10.dp, top = 10.dp),
        onClick = { navController.navigate("home")}) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close"
        )
    }
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            onClick = {
                createPost(text.value)
                navController.navigate("home")
            },
            modifier = Modifier
                .background(shape = RoundedCornerShape(50), color = Color.Transparent)
                .padding(end = 10.dp, top = 10.dp)
                .align(alignment = Alignment.End),
            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.background),
            shape = CircleShape
        ) {
            Text("com/example/shebetar/Post", color = MaterialTheme.colorScheme.surface)

        }
        TextField(
            value = text.value,
            onValueChange = { newText -> text.value = newText },
            label = { Text("Enter post's text") },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
        )
    }
}