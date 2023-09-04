package com.example.shebetar.HomeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shebetar.Classes.Comment.Comment
import com.example.shebetar.Classes.Post.Post
import com.example.shebetar.Classes.User.User
import com.example.shebetar.DataBase.addUser
import com.example.shebetar.DataBase.getUser
import com.example.shebetar.TopNavBar.TopNavBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date

@Composable
fun HomeScreen(navController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState) {
    Column {
        TopNavBar(scope = scope, scaffoldState = scaffoldState)
        LazyColumn(
            modifier        = Modifier.fillMaxSize(),
            contentPadding  = PaddingValues(10.dp)
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
                val user = User()
                runBlocking{launch{ addUser(user) }}


                navController.navigate("postCreation")
            }) {
            Icon(
                Icons.Filled.Add,
                contentDescription  = "Create post",
                tint                = MaterialTheme.colorScheme.surface
            )
        }
    }
}

fun createPost(text: String){
    var user = User()
    runBlocking { launch { user = getUser() } }
    val post = Post(posts.last().id, user, text, Date(), 0, emptyList<User>(), 0, emptyList<User>(), 0, emptyList<Comment>(), 0)
    posts += post
}