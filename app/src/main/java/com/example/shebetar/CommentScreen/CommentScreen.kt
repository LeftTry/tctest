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
import com.example.shebetar.Classes.Post.Post
import com.example.shebetar.HomeScreen.PostItem

@Composable
fun CommentScreen(postId: String?){
    val commentText = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PostItem(post = )
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            TextField(value = commentText.value, onValueChange = {commentTextNew -> commentText.value = commentTextNew}, label = { Text(
                text = "CommentText"
            )}, shape = CircleShape, modifier = Modifier.padding(10.dp))
        }
    }
}