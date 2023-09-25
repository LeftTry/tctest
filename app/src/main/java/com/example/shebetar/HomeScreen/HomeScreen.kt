package com.example.shebetar.HomeScreen

import android.content.Context
import android.util.Log
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
import com.example.shebetar.Classes.Post.Post
import com.example.shebetar.Classes.User.User
import com.example.shebetar.DataBase.getUserById
import com.example.shebetar.DataBase.readUserDataFromJson
import com.example.shebetar.DataBase.updateUser
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

fun createPost(text: String, context: Context){
    val user = User()
    runBlocking { launch {
        user.toUserFromDocumentSnapshot(getUserById(readUserDataFromJson("LoginedUser.json", context)?.id)) } }
    val post = Post(posts.last().id, user.id, text, Date(), 0, emptyList(), 0, emptyList(), 0, emptyList(), 0)
    if(user.posts.isEmpty()){
        user.posts = listOf(post.id)
    } else {
        user.posts += post.id
    }
    Log.d("CreatePostUserPostsLastPostText", user.posts.last().toString())
    runBlocking { launch { updateUser(user) } }
    posts += post
}