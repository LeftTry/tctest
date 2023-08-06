package com.example.shebetar.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            IconButton(
                onClick = { scope.launch{ scaffoldState.drawerState.open() } }) {
                Icon(imageVector = Icons.Default.List, contentDescription = "LeftSideMenu",
                    tint = MaterialTheme.colorScheme.surface)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(all = 16.dp),
            onClick = {
                navController.navigate("postCreation")
            }) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Create post",
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }
}

fun createPost(text: String){
    val post = Post(posts.last().id, text, 0, 0, 0, 0)
    posts += post
}