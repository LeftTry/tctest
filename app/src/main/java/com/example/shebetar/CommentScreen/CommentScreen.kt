package com.example.shebetar.CommentScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shebetar.Classes.Post.Post
import com.example.shebetar.DataBase.getPostDB
import com.example.shebetar.HomeScreen.PostItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun CommentScreen(postId: String?, navController: NavHostController){
    val commentText = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var post = Post()
        runBlocking { launch { post = getPostDB(postId!!.toLong()) } }
        PostItem(post = post, navController = navController)
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            TextField(value = commentText.value, onValueChange = {commentTextNew -> commentText.value = commentTextNew}, label = { Text(
                text = "CommentText"
            )}, shape = CircleShape, modifier = Modifier.padding(10.dp))
        }
    }
}